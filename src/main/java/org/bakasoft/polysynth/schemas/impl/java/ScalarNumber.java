package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphNumber;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.ConversionCache;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;

import java.math.BigDecimal;

public class ScalarNumber extends Schema {

  @Override
  public Number convert(Object value, ConversionCache cache) {
    if (value == null) {
      return null;
    }
    else if (value instanceof Number) {
      return (Number)value;
    }
    else if (value instanceof CharSequence) {
      return new BigDecimal(((CharSequence)value).toString());
    }

    throw new ConversionException(value, Number.class);
  }

  @Override
  public String toString() {
    return "Scalar<Number>";
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    return new GraphNumber(convert(instance, null));
  }

  @Override
  public Class<?> getType() {
    return Number.class;
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
