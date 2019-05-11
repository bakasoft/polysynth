package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphString;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.ConversionCache;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;

public class ScalarString extends Schema {

  @Override
  public String convert(Object value, ConversionCache cache) {
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
    return new GraphString(convert(instance, null));
  }

  @Override
  public Class<?> getType() {
    return String.class;
  }

  @Override
  public GraphValue toGraph(Object instance) {
    return null;
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
