package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphBoolean;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ScalarSchema;

public class ScalarBoolean implements ScalarSchema {

  @Override
  public Boolean convert(Object value) {
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
    return new GraphBoolean(convert(instance));
  }

  @Override
  public Class<?> getType() {
    return Boolean.class;
  }
}
