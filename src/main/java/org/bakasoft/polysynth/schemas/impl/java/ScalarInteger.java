package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphNumber;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.ConversionCache;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;

public class ScalarInteger extends Schema {

  @Override
  public Integer convert(Object value, ConversionCache cache) {
    if (value == null) {
      return null; // java.lang.Integer can be null
    }
    else if (value instanceof Integer) {
      return (Integer)value;
    }

    try {
      return Integer.parseInt(value.toString());
    }
    catch (NumberFormatException e) {
      throw new ConversionException(value, Integer.class, e);
    }
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    Integer i = convert(instance, null);

    if (i == null) {
      return null;
    }

    return new GraphNumber(i);
  }

  @Override
  public Class<?> getType() {
    return Integer.class;
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
