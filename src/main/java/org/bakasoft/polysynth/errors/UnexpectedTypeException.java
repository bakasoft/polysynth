package org.bakasoft.polysynth.errors;

public class UnexpectedTypeException extends PolysynthException {

  public UnexpectedTypeException(Object instance, Object expectedType) {
    super(String.format(
        "Unexpected type of %s (expected: %s)", // TODO improve this message
        inspect(instance),
        inspect(expectedType)));
  }

}
