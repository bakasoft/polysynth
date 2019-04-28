package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;

import java.lang.reflect.Field;

public class FieldPropertyHelper {
  public static FieldProperty create(Class<?> type, String fieldName) {
    Field field = null;

    for (Field f : type.getDeclaredFields()) {
      if (fieldName.equals(f.getName())) {
        field = f;
        break;
      }
    }

    if (field == null) {
      throw new AssertionError("Field not found: " + fieldName);
    }

    return new FieldProperty(new Polysynth(), type, fieldName, field);
  }
}
