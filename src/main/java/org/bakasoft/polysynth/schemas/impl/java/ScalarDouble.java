package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphNumber;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ScalarSchema;

public class ScalarDouble implements ScalarSchema {

  @Override
  public Double convert(Object value) {
    if (value == null) {
      return null; // java.lang.Double can be null
    }
    else if (value instanceof Double) {
      return (Double)value;
    }

    try {
      return Double.parseDouble(value.toString());
    }
    catch (NumberFormatException e) {
      throw new ConversionException(value, Double.class, e);
    }
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    Double d = convert(instance);

    if (d == null) {
      return null;
    }

    return new GraphNumber(d);
  }

  @Override
  public Class<?> getType() {
    return Double.class;
  }
}
