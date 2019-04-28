package org.bakasoft.polysynth.errors;

public class MissingConstructorException extends PolysynthException {

  public MissingConstructorException(Class<?> type) {
    super(String.format(
        "The type %s does not provide a default constructor.",
        inspect(type)));
  }
}
