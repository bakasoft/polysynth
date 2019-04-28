package org.bakasoft.polysynth.errors;

public class UnknownPropertyException extends PolysynthException {
  public UnknownPropertyException(Class<?> type, String propertyKey) {
    super(String.format(
        "Unknown property %s for the type %s.",
        inspect(propertyKey),
        inspect(type)));
  }
}
