package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphValue;

import java.util.HashMap;
import java.util.Map;

abstract public class Schema {

  public final Object convert(Object value) {
    return convert(value, null);
  }

  abstract protected Object convert(Object value, ConversionCache cache);

  abstract public GraphValue toGraph(Object instance, GraphContext context);

  abstract public Class<?> getType();

  abstract public GraphValue toGraph(Object instance);

  abstract public ObjectSchema toObject();

  abstract public ArraySchema toArray();

}
