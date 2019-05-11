package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphNumber;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.ConversionCache;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;

public class Scalar_int extends Schema {

  @Override
  public Integer convert(Object value, ConversionCache cache) {
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
    return new GraphNumber(convert(instance, null));
  }

  @Override
  public Class<?> getType() {
    return int.class;
  }

  @Override
  public GraphValue toGraph(Object instance) {
    return new GraphNumber(convert(instance, null));
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
    return "Scalar<int>";
  }
}
