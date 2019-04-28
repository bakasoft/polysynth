package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphNumber;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ScalarSchema;

public class Scalar_int implements ScalarSchema {

  @Override
  public Integer convert(Object value) {
    if (value == null) {
      throw new ConversionException(null, int.class);
    }
    try {
      return Integer.parseInt(value.toString());
    }
    catch (NumberFormatException e) {
      throw new ConversionException(value, int.class, e);
    }
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    return new GraphNumber(convert(instance));
  }

  @Override
  public Class<?> getType() {
    return int.class;
  }

  @Override
  public String toString() {
    return "Scalar<int>";
  }
}
