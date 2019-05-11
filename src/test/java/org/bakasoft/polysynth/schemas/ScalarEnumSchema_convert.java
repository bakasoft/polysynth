package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.schemas.EnumSchema;

import java.lang.annotation.ElementType;

public class ScalarEnumSchema_convert extends TestCase {{
  EnumSchema schema = new EnumSchema(ElementType.class);

  // null is converted to null
  expect(schema.convert(null)).toBeNull();

  // enum instances are just returned directly
  expect(schema.convert(ElementType.FIELD)).toBe(ElementType.FIELD);

  // valid strings are parsed into a value
  expect(schema.convert("ANNOTATION_TYPE")).toBe(ElementType.ANNOTATION_TYPE);

  // invalid strings can't be parsed
  fail(ConversionException.class, () -> {
    schema.convert("FOO BAR");
  });

  // other values can't be converted
  fail(ConversionException.class, () -> {
    schema.convert(1);
  });
}}
