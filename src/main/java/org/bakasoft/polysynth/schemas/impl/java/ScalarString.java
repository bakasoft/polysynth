package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphString;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ScalarSchema;

public class ScalarString implements ScalarSchema {

  @Override
  public String convert(Object value) {
    if (value == null) {
      return null;
    }

    return value.toString();
  }

  @Override
  public String toString() {
    return "Scalar<String>";
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    return new GraphString(convert(instance));
  }

  @Override
  public Class<?> getType() {
    return String.class;
  }
}
