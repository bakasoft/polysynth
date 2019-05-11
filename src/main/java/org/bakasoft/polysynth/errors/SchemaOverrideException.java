package org.bakasoft.polysynth.errors;

import java.lang.reflect.Type;

public class SchemaOverrideException extends PolysynthException {
  public SchemaOverrideException(Type type) {
    super(String.format("The schema for type '%s' cannot be overridden.", type.getTypeName()));
  }
}
