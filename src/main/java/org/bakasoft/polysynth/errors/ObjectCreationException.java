package org.bakasoft.polysynth.errors;

public class ObjectCreationException extends PolysynthException {

  public ObjectCreationException(Class<?> type) {
    this(type, null);
  }

  public ObjectCreationException(Class<?> type, Throwable cause) {
    super(String.format(
        "Cannot create an instance of the type %s.",
        inspect(type)), cause);
  }

}
