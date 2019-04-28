package org.bakasoft.polysynth.errors;

public class SchemaOverrideException extends PolysynthException {
  public SchemaOverrideException(Class<?> type) {
    super(String.format("The schema for type '%s' cannot be overridden.", type.getName()));
  }
}
