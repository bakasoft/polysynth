package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.AmbiguousPropertyException;
import org.bakasoft.polysynth.schemas.Schema;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

abstract public class ClassProperty {

  abstract public boolean isReadOnly();
  abstract public boolean isWriteOnly();
  abstract public void set(Object instance, Object value);
  abstract public Object get(Object instance);
  abstract public Schema getSchema();

  static class PropertyData {
    String name;
    Method getter;
    Class<?> getterType;
    Method setter;
    Class<?> setterType;
    Field field;
  }

  // TODO what about indexed properties?
  public static Map<String, ClassProperty> generateProperties(Class<?> type, Polysynth polysynth) {
    LinkedHashMap<String, ClassProperty> properties = new LinkedHashMap<>();
    LinkedHashMap<String, PropertyData> data = new LinkedHashMap<>();
    Function<String, PropertyData> getData = key -> data.computeIfAbsent(key, k -> new PropertyData());

    for (Field field : type.getFields()) {
      // exclude static fields
      if (!Modifier.isStatic(field.getModifiers())) {
        String name = field.getName();
        PropertyData pd = getData.apply(name);

        pd.name = name;
        pd.field = field;
      }
    }

    for (Method method : type.getMethods()) {
      // exclude static and Object methods
      if (!Modifier.isStatic(method.getModifiers()) && method.getDeclaringClass() != Object.class) {
        if (method.getParameterCount() == 0 && method.getReturnType() != void.class) {
          String getterName = getGetterNameOrNull(method.getName());

          if (getterName != null) {
            PropertyData pd = getData.apply(getterName);

            if (pd.getter != null) {
              throw new AmbiguousPropertyException(type, getterName, "getter is defined twice");
            }

            pd.name = getterName;
            pd.getter = method;
            pd.getterType = method.getReturnType();
          }
        }
        else if (method.getParameterCount() == 1 && method.getReturnType() == void.class) {
          String setterName = getSetterNameOrNull(method.getName());

          if (setterName != null) {
            PropertyData pd = getData.apply(setterName);

            if (pd.setter != null) {
              throw new AmbiguousPropertyException(type, setterName, "setter is defined twice");
            }

            pd.name = setterName;
            pd.setter = method;
            pd.setterType = method.getParameters()[0].getType();
          }
        }
      }
    }

    data.forEach((key, pd) -> {
      ClassProperty property;

      if (pd.field != null && pd.getter == null && pd.setter == null) {
        property = new FieldProperty(polysynth, type, pd.name, pd.field);
      }
      else if ((pd.getter != null || pd.setter != null) && pd.field == null) {
        property = new MethodProperty(polysynth, type, pd.name, pd.getter, pd.getterType, pd.setter, pd.setterType);
      }
      else {
        throw new AmbiguousPropertyException(type, pd.name, "is defined both as field and getter/setter");
      }

      properties.put(key, property);
    });

    return properties;
  }

  private static String getSetterNameOrNull(String name) {
    if (name.startsWith("set") && name.length() > 3) {
      return fieldCase(name.substring(3));
    }

    return null;
  }

  private static String getGetterNameOrNull(String name) {
    if (name.startsWith("get") && name.length() > 3) {
      return fieldCase(name.substring(3));
    }
    else if (name.startsWith("is") && name.length() > 2) {
      return fieldCase(name.substring(2));
    }

    return null;
  }

  private static String fieldCase(String name) {
    if (name.length() >= 1) {
      char first = name.charAt(0);

      if (first >= 'A' && first <= 'Z') {
        if (name.length() >= 2) {
          char second = name.charAt(1);

          // don't do it if the two first letters are uppercase
          if (second >= 'A' && second <= 'Z') {
            return name;
          }
        }

        return Character.toLowerCase(first) + name.substring(1);
      }
    }

    return name;
  }


}
