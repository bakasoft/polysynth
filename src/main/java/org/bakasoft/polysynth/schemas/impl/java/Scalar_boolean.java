package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphBoolean;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.ConversionCache;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;

public class Scalar_boolean extends Schema {

  @Override
  public Boolean convert(Object value, ConversionCache cache) {
    if (value == null) {
      throw new ConversionException(null, boolean.class);
    }
    else if (value instanceof Boolean) {
      return (Boolean)value;
    }

    if ("true".equals(value)) {
      return true;
    }
    else if ("false".equals(value)) {
      return false;
    }

    throw new ConversionException(value, boolean.class);
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    return new GraphBoolean(convert(instance, null));
  }

  @Override
  public Class<?> getType() {
    return boolean.class;
  }

  @Override
  public GraphValue toGraph(Object instance) {
    return new GraphBoolean(convert(instance, null));
  }

  @Override
  public ObjectSchema toObject() {
    return null;
  }

  @Override
  public ArraySchema toArray() {
    return null;
  }

  @Override
  public String toString() {
    return "Scalar<boolean>";
  }
}
