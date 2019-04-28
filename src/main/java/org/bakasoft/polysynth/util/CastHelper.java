package org.bakasoft.polysynth.util;

import org.bakasoft.polysynth.errors.UnexpectedTypeException;

import java.util.List;

public class CastHelper {
  public static List<?> safeList(Object instance) {
    if (!(instance instanceof List)) {
      throw new UnexpectedTypeException(instance, List.class);
    }

    return (List<?>)instance;
  }

  @SuppressWarnings("unchecked")
  public static <T> List<T> unsafeList(Object instance) {
    return (List<T>) safeList(instance);
  }
}
