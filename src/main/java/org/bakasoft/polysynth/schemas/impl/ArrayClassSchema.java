package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.ArrayMutationException;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.graph.GraphArray;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.Schema;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayClassSchema implements ArraySchema {

  private final Class<?> type;
  private final Class<?> componentType;
  private final Polysynth polysynth;

  public ArrayClassSchema(Polysynth polysynth, Class<?> type) {
    this.polysynth = polysynth;
    this.type = type;
    this.componentType = type.getComponentType();

    if (componentType == null) {
      throw new UnexpectedTypeException(type, "array");
    }
  }

  @Override
  public void add(Object instance, Object value) {
    throw new ArrayMutationException("cannot increase the size of static arrays");
  }

  @Override
  public List<Object> list(Object instance) {
    if (instance == null) { throw new MissingArgumentException("instance"); }

    int length = Array.getLength(instance);
    ArrayList<Object> result = new ArrayList<>(length);

    for (int i = 0; i < length; i++) {
      Object item = Array.get(instance, i);

      result.add(item);
    }

    return result;
  }

  @Override
  public Schema getItemSchema() {
    return polysynth.getSchema(componentType);
  }

  @Override
  public Object createWith(List<?> items) {
    if (items == null || items.isEmpty()) {
      return Array.newInstance(componentType, 0);
    }

    int length = items.size();
    Object array = Array.newInstance(componentType, length);

    for (int i = 0; i < length; i++) {
      Object item = items.get(i);

      if (!componentType.isInstance(item)) {
        throw new UnexpectedTypeException(item, componentType);
      }

      Array.set(array, i, item);
    }

    return array;
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    return new GraphArray(context, this, instance);
  }

  @Override
  public Class<?> getType() {
    return type;
  }

  @Override
  public String toString() {
    return "Array<" + componentType.getSimpleName() + ">";
  }
}
