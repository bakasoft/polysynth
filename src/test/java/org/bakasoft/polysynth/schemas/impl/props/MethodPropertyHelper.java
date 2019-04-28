package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;

import java.lang.reflect.Method;

public class MethodPropertyHelper {
  public static MethodProperty create(Class<?> type, String propertyName, String getterName, String setterName) {
    Method getter = null;
    Method setter = null;

    for (Method f : type.getDeclaredMethods()) {
      if (getterName.equals(f.getName())) {
        getter = f;
      }
      else if (setterName.equals(f.getName())) {
        setter = f;
      }
    }

    return new MethodProperty(
        new Polysynth(),
        type,
        propertyName,
        getter, getter != null ? getter.getReturnType() : null,
        setter, setter != null ? setter.getParameterTypes()[0] : null);
  }
}
