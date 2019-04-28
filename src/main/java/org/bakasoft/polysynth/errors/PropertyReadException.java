package org.bakasoft.polysynth.errors;

public class PropertyReadException extends PolysynthException {
  public PropertyReadException(Class<?> type, String propertyKey) {
    this(type, propertyKey, null);
  }
  public PropertyReadException(Class<?> type, String propertyKey, Throwable cause) {
    super(String.format(
        "Cannot read property %s of type %s.",
        inspect(propertyKey),
        inspect(type)), cause);
  }
}
