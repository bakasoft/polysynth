package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.schemas.EnumSchema;

public class ScalarEnumSchema_toString extends TestCase {{
  EnumSchema schema = new EnumSchema(Character.UnicodeScript.class);

  expect(schema.toString()).toEqual("Enum<UnicodeScript>");
}}
