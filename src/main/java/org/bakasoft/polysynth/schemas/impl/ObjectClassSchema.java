package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.UnknownPropertyException;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphObject;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;
import org.bakasoft.polysynth.schemas.impl.props.ClassProperty;
import org.bakasoft.polysynth.util.ReflectionHelper;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class ObjectClassSchema implements ObjectSchema {

  private final Polysynth polysynth;
  private final Class<?> type;
  private Supplier<Object> creator;

  private Map<String, ClassProperty> properties;

  public ObjectClassSchema(Polysynth polysynth, Class<?> type) {
    if (polysynth == null) { throw new MissingArgumentException("polysynth"); }
    if (type == null) { throw new MissingArgumentException("type"); }

    this.polysynth = polysynth;
    this.type = type;
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    return new GraphObject(context, this, instance);
  }

  @Override
  public Class<?> getType() {
    return type;
  }

  private Map<String, ClassProperty> lazyLoading() {
    if (properties == null) {
      properties = ClassProperty.generateProperties(type, polysynth);
    }

    return properties;
  }

  @Override
  public Set<String> getKeys() {
    return lazyLoading().keySet();
  }

  @Override
  public Schema getSchema(String key) {
    ClassProperty p = lazyLoading().get(key);

    if (p == null) {
      return null;
    }

    return p.getSchema();
  }

  @Override
  public boolean isReadOnly(String key) {
    ClassProperty p = lazyLoading().get(key);

    if (p == null) {
      throw new UnknownPropertyException(type, key);
    }

    return p.isReadOnly();
  }

  @Override
  public boolean isWriteOnly(String key) {
    ClassProperty p = lazyLoading().get(key);

    if (p == null) {
      throw new UnknownPropertyException(type, key);
    }

    return p.isWriteOnly();
  }

  @Override
  public Object createWith(Map<String, ?> initialValues) {
    if (creator == null) {
      creator = ReflectionHelper.makeObjectCreator(type);
    }

    Object instance = creator.get();

    if (initialValues != null) {
      initialValues.forEach((key, value) -> set(instance, key, value));
    }

    return instance;
  }

  @Override
  public void set(Object instance, String key, Object value) {
    if (instance == null) { throw new MissingArgumentException("instance"); }

    ClassProperty p = lazyLoading().get(key);

    if (p == null) {
      throw new UnknownPropertyException(type, key);
    }

    p.set(instance, value);
  }

  @Override
  public Object get(Object instance, String key) {
    if (instance == null) { throw new MissingArgumentException("instance"); }

    ClassProperty p = lazyLoading().get(key);

    if (p == null) {
      throw new UnknownPropertyException(type, key);
    }

    return p.get(instance);
  }

  @Override
  public String toString() {
    return "Object<" + type.getSimpleName() + ">";
  }
}
