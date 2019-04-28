package org.bakasoft.polysynth.errors;

public class ConversionException extends PolysynthException {

  public ConversionException(Object sourceValue, Class<?> targetType) {
    this(sourceValue, targetType, null);
  }

  public ConversionException(Object sourceValue, Class<?> targetType, Throwable cause) {
    super(String.format("Cannot convert the value %s to the type %s",
        inspect(sourceValue),
        inspect(targetType)), cause);
  }

}
