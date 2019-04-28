package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.graph.GraphArray;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.Schema;
import org.bakasoft.polysynth.util.CastHelper;
import org.bakasoft.polysynth.util.ReflectionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ArrayListSchema implements ArraySchema {

  private final Class<?> type;
  private final Polysynth polysynth;

  private Supplier<? extends List<?>> creator;

  public ArrayListSchema(Polysynth polysynth, Class<?> type) {
    if (polysynth == null) { throw new MissingArgumentException("polysynth"); }
    if (type == null) { throw new MissingArgumentException("type"); }

    this.polysynth = polysynth;
    this.type = type;
  }

  @Override
  public void add(Object instance, Object value) {
    if (instance == null) { throw new MissingArgumentException("instance"); }

    CastHelper.unsafeList(instance).add(value);
  }

  @Override
  public List<Object> list(Object instance) {
    if (instance == null) { throw new MissingArgumentException("instance"); }
    if (!type.isInstance(instance)) {
      throw new UnexpectedTypeException(instance, type);
    }

    List<?> list = CastHelper.safeList(instance);

    return new ArrayList<>(list);
  }

  @Override
  public Schema getItemSchema() {
    // TODO sometimes it is actually possible to have the item type
    return null;
  }

  @Override
  public Object createWith(List<?> items) {
    if (creator == null) {
      creator = ReflectionHelper.makeListConstructor(type);
    }

    List<Object> list = CastHelper.unsafeList(creator.get());

    if (items != null) {
      list.addAll(items);
    }

    return list;
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
    return "Array<" + type.getSimpleName() + ">";
  }
}

