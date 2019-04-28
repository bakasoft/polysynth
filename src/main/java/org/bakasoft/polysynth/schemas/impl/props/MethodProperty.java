package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.AmbiguousPropertyException;
import org.bakasoft.polysynth.errors.PropertyReadException;
import org.bakasoft.polysynth.errors.PropertyWriteException;
import org.bakasoft.polysynth.schemas.Schema;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodProperty extends ClassProperty {

  private final Polysynth polysynth;
  private final Class<?> parent;
  private final String name;
  private final Class<?> type;
  private final Method getter;
  private final Method setter;

  public MethodProperty(Polysynth polysynth, Class<?> parent, String name, Method getter, Class<?> getterType, Method setter, Class<?> setterType) {
    this.polysynth = polysynth;
    this.name = name;
    this.parent = parent;
    this.getter = getter;
    this.setter = setter;

    if (getterType != null && setterType != null) {
      if (getterType.isAssignableFrom(setterType)) {
        type = getterType;
      }
      else {
        throw new AmbiguousPropertyException(parent, name, "incompatible setter & getter types");
      }
    }
    else if (getterType != null) {
      type = getterType;
    }
    else if (setterType != null) {
      type = setterType;
    }
    else {
      throw new AmbiguousPropertyException(parent, name, "cannot determine the property type");
    }
  }
  
  @Override
  public boolean isReadOnly() {
    return getter != null && setter == null;
  }

  @Override
  public boolean isWriteOnly() {
    return setter != null && getter == null;
  }

  @Override
  public void set(Object instance, Object value) {
    if (setter == null) {
      throw new PropertyWriteException(parent, name);
    }

    try {
      setter.invoke(instance, value);
    } catch (IllegalAccessException e) {
      throw new PropertyWriteException(parent, name, e);
    } catch (InvocationTargetException e) {
      Throwable original = e.getTargetException();
      throw new PropertyWriteException(parent, name, original != null ? original : e);
    }
  }

  @Override
  public Object get(Object instance) {
    if (getter == null) {
      throw new PropertyReadException(parent, name);
    }

    try {
      return getter.invoke(instance);
    } catch (IllegalAccessException e) {
      throw new PropertyReadException(parent, name, e);
    } catch (InvocationTargetException e) {
      Throwable original = e.getTargetException();
      throw new PropertyReadException(parent, name, original != null ? original : e);
    }
  }

  @Override
  public Schema getSchema() {
    return polysynth.getSchema(type);
  }
}