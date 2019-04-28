package org.bakasoft.polysynth.errors;

public class PropertyWriteException extends PolysynthException {
  public PropertyWriteException(Class<?> type, String propertyKey) {
    this(type, propertyKey, null);
  }

  public PropertyWriteException(Class<?> type, String propertyKey, Throwable cause) {
    super(String.format(
        "Cannot write property %s of type %s.",
        inspect(propertyKey),
        inspect(type)), cause);
  }
}
