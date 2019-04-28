package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.errors.*;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphString;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ScalarSchema;

import java.util.HashMap;

public class ScalarEnumSchema implements ScalarSchema {

  private final Class<?> type;

  private HashMap<String, Object> values_;

  public ScalarEnumSchema(Class<?> type) {
    if (type == null) { throw new MissingArgumentException("type"); }
    if (!type.isEnum()) { throw new UnexpectedTypeException(type, Enum.class); }

    this.type = type;
  }

  synchronized private HashMap<String, Object> lazyLoad() {
    if (values_ == null) {
      Object[] items = type.getEnumConstants();

      values_ = new HashMap<>();

      if (items != null) {
        for (Object item : items) {
          String name = ((Enum<?>) item).name();

          values_.put(name, item);
        }
      }
    }

    return values_;
  }

  @Override
  public Object convert(Object value) {
    if (value == null) {
      return null;
    }
    else if (type.isInstance(value)) {
      return value;
    }

    String name = value.toString();

    if (lazyLoad().containsKey(name)) {
      return lazyLoad().get(name);
    }

    throw new ConversionException(value, type);
  }

  @Override
  public String toString() {
    return "Enum<" + type.getSimpleName() + ">";
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    Object value = convert(instance);

    if (value == null) {
      return null;
    }

    Enum<?> item = (Enum<?>)value;

    return new GraphString(item.name());
  }

  @Override
  public Class<?> getType() {
    return type;
  }
}
