package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;

public class ScalarEnumSchema_toString extends TestCase {{
  EnumSchema schema = new EnumSchema(Character.UnicodeScript.class);

  expect(schema.toString()).toEqual("Enum<UnicodeScript>");
}}
