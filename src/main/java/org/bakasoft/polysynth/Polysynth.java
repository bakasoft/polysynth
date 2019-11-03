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
import java.util.HashMap;

public class Polysynth {

  private final HashMap<Type, Schema> schemas;

  public Polysynth() {
    this(true);
  }

  public Polysynth(boolean addDefaultSchemas) {
    schemas = new HashMap<>();

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

    synchronized(schemas) {
      if (schemas.containsKey(type)) {
        throw new SchemaOverrideException(type);
      }

      schemas.put(type, schema);
    }
  }

  public Schema getSchema(Type type) {
    if (type == null) { throw new MissingArgumentException("type"); }

    synchronized(schemas) {
      // TODO what happens if the type is abstract or an interface?
      if (schemas.containsKey(type)) {
        return schemas.get(type);
      }

      BeatType beatType = new BeatType(type);
      Schema schema;

      if (beatType.isCollection() || beatType.isArray()) {
        schema = new ArraySchema(this, beatType);
      }
      else {
        Class<?> typeClass = beatType.getTypeClass();

        if (typeClass.isEnum()) {
          schema = new EnumSchema(typeClass);
        } else if (typeClass.isPrimitive()) {
          if (typeClass == int.class) {
            schema = new Scalar_int();
          } else if (typeClass == boolean.class) {
            schema = new Scalar_boolean();
          }
          // TODO add missing schemas
          else if (typeClass == char.class) {
            throw new UnsupportedOperationException();
          } else if (typeClass == long.class) {
            throw new UnsupportedOperationException();
          } else if (typeClass == double.class) {
            throw new UnsupportedOperationException();
          } else if (typeClass == float.class) {
            throw new UnsupportedOperationException();
          } else if (typeClass == byte.class) {
            throw new UnsupportedOperationException();
          } else if (typeClass == short.class) {
            throw new UnsupportedOperationException();
          }
          else {
            throw new UnsupportedOperationException();
          }
        }
        else {
          schema = new ObjectSchema(this, beatType);
        }
      }

      schemas.put(type, schema);

      return schema;
    }
  }

}
