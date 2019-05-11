package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphBoolean;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.ConversionCache;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;

public class ScalarBoolean extends Schema {

  @Override
  public Boolean convert(Object value, ConversionCache cache) {
    if (value == null) {
      return null;
    }
    else if (value == Boolean.TRUE || "true".equals(value)) {
      return true;
    }
    else if (value == Boolean.FALSE || "false".equals(value)) {
      return false;
    }

    throw new ConversionException(value, Boolean.class);
  }

  @Override
  public String toString() {
    return "Scalar<Boolean>";
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    return new GraphBoolean(convert(instance, null));
  }

  @Override
  public Class<?> getType() {
    return Boolean.class;
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
}
