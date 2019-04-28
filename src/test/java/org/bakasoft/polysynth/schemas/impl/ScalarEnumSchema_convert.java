package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.ConversionException;

import java.lang.annotation.ElementType;

public class ScalarEnumSchema_convert extends TestCase {{
  ScalarEnumSchema schema = new ScalarEnumSchema(ElementType.class);

  // null is converted to null
  itMustBeEqual(schema.convert(null), null);

  // enum instances are just returned directly
  itMustBeSame(schema.convert(ElementType.FIELD), ElementType.FIELD);

  // valid strings are parsed into a value
  itMustBeEqual(schema.convert("ANNOTATION_TYPE"), ElementType.ANNOTATION_TYPE);

  // invalid strings can't be parsed
  itMustFailWith(ConversionException.class, () -> {
    schema.convert("FOO BAR");
  });

  // other values can't be converted
  itMustFailWith(ConversionException.class, () -> {
    schema.convert(1);
  });
}}
