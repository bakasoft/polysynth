package org.bakasoft.polysynth;

import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.SchemaOverrideException;
import org.bakasoft.polysynth.schemas.impl.DefaultSchemas;
import org.bakasoft.polysynth.schemas.Schema;
import org.bakasoft.polysynth.schemas.impl.ArrayClassSchema;
import org.bakasoft.polysynth.schemas.impl.ArrayListSchema;
import org.bakasoft.polysynth.schemas.impl.ObjectClassSchema;
import org.bakasoft.polysynth.schemas.impl.ScalarEnumSchema;
import org.bakasoft.polysynth.schemas.impl.java.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Polysynth {

  private final ConcurrentHashMap<Class<?>, Schema> schemas;

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
        .toGraph(instance, this);
  }

  public void setSchema(Class<?> type, Schema schema) {
    if (type == null) { throw new MissingArgumentException("type"); }
    if (schema == null) { throw new MissingArgumentException("schema"); }

    if (schemas.containsKey(type)) {
      throw new SchemaOverrideException(type);
    }

    schemas.put(type, schema);
  }

  public Schema getSchema(Class<?> type) {
    if (type == null) { throw new MissingArgumentException("type"); }

    // TODO what happens if the type is abstract or an interface?
    return schemas.computeIfAbsent(type, t -> {
      if (t.isArray()) {
        return new ArrayClassSchema(this, t);
      }
      else if (t.isEnum()) {
        return new ScalarEnumSchema(t);
      }
      else if (t.isPrimitive()) {
        if (t == int.class) { return new Scalar_int(); }
        else if (t == boolean.class) { return new Scalar_boolean(); }
        // TODO add missing schemas
        else if (t == char.class) { throw new UnsupportedOperationException(); }
        else if (t == long.class) { throw new UnsupportedOperationException(); }
        else if (t == double.class) { throw new UnsupportedOperationException(); }
        else if (t == float.class) { throw new UnsupportedOperationException(); }
        else if (t == byte.class) { throw new UnsupportedOperationException(); }
        else if (t == short.class) { throw new UnsupportedOperationException(); }
      }
      else if (List.class.isAssignableFrom(t)) {
        return new ArrayListSchema(this, t);
      }

      return new ObjectClassSchema(this, t);
    });
  }

}
