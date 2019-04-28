package org.bakasoft.polysynth.errors;

public class MissingArgumentException extends PolysynthException {
  public MissingArgumentException(String name) {
    super(String.format(
        "Missing argument: %s",
        name));
  }
}
