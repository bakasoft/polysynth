package org.bakasoft.polysynth;

import org.bakasoft.beat.BeatType;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.SchemaOverrideException;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.EnumSchema;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;
import org.bakasoft.polysynth.schemas.impl.DefaultSchemas;
import org.bakasoft.polysynth.schemas.impl.java.*;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Polysynth {

  private final Map<Type, Schema> schemas;

  public Polysynth() {
    this(true);
  }

  public Polysynth(boolean addDefaultSchemas) {
    schemas = new ConcurrentHashMap<>();

    if (addDefaultSchemas) {
      DefaultSchemas.fill(schemas);
    }
  }

  public Object toGraph(Object instance) {
    if (instance == null) {
      return null;
    }

    return getSchema(instance.getClass())
        .toGraph(instance, new GraphContext(this));
  }

  public void setSchema(Type type, Schema schema) {
    if (type == null) { throw new MissingArgumentException("type"); }
    if (schema == null) { throw new MissingArgumentException("schema"); }

    synchronized(this) {
      if (schemas.containsKey(type)) {
        throw new SchemaOverrideException(type);
      }

      schemas.put(type, schema);
    }
  }

  public Schema getSchema(Type type) {
    if (type == null) { throw new MissingArgumentException("type"); }

    // TODO what happens if the type is abstract or an interface?
    return schemas.computeIfAbsent(type, t -> {
      BeatType beatType = new BeatType(t);

      if (beatType.isCollection() || beatType.isArray()) {
        return new ArraySchema(this, beatType);
      }

      Class<?> typeClass = beatType.getTypeClass();

      if (typeClass.isEnum()) {
        return new EnumSchema(typeClass);
      }
      else if (typeClass.isPrimitive()) {
        if (typeClass == int.class) { return new Scalar_int(); }
        else if (typeClass == boolean.class) { return new Scalar_boolean(); }
        // TODO add missing schemas
        else if (typeClass == char.class) { throw new UnsupportedOperationException(); }
        else if (typeClass == long.class) { throw new UnsupportedOperationException(); }
        else if (typeClass == double.class) { throw new UnsupportedOperationException(); }
        else if (typeClass == float.class) { throw new UnsupportedOperationException(); }
        else if (typeClass == byte.class) { throw new UnsupportedOperationException(); }
        else if (typeClass == short.class) { throw new UnsupportedOperationException(); }
      }

      return new ObjectSchema(this, beatType);
    });
  }

}
