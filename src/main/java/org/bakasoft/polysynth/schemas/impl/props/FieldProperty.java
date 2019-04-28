package org.bakasoft.polysynth.schemas.impl.props;


import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.PropertyReadException;
import org.bakasoft.polysynth.errors.PropertyWriteException;
import org.bakasoft.polysynth.schemas.Schema;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldProperty extends ClassProperty {

  private final Polysynth polysynth;
  private final String name;
  private final Class<?> parent;
  private final Field field;
  private final Class<?> type;
  private final boolean readOnly;

  public FieldProperty(Polysynth polysynth, Class<?> parent, String name, Field field) {
    this.polysynth = polysynth;
    this.name = name;
    this.parent = parent;
    this.field = field;
    this.type = field.getType();
    this.readOnly = Modifier.isFinal(field.getModifiers());
  }

  @Override
  public boolean isReadOnly() {
    return readOnly;
  }

  @Override
  public boolean isWriteOnly() {
    return false;
  }

  @Override
  public void set(Object instance, Object value) {
    if (readOnly) {
      throw new PropertyWriteException(parent, name);
    }

    try {
      field.set(instance, value);
    }
    catch (IllegalAccessException e) {
      throw new PropertyWriteException(parent, name, e);
    }
  }

  @Override
  public Object get(Object instance) {
    try {
      return field.get(instance);
    } catch (IllegalAccessException e) {
      throw new PropertyReadException(parent, name, e);
    }
  }

  @Override
  public Schema getSchema() {
    return polysynth.getSchema(type);
  }
}
