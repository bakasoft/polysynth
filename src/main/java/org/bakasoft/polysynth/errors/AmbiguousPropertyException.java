package org.bakasoft.polysynth.errors;

public class AmbiguousPropertyException extends PolysynthException {
  public AmbiguousPropertyException(Class<?> type, String propertyKey, String description) {
    super(String.format(
        "The property %s of the type %s is ambiguous: %s",
        inspect(type),
        inspect(propertyKey),
        inspect(description)));
  }
}
