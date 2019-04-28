package org.bakasoft.polysynth.errors;

public class ArrayMutationException extends PolysynthException {
  public ArrayMutationException(String description) {
    super(String.format(
        "Error mutating array: %s",
        inspect(description)
    ));
  }
}
