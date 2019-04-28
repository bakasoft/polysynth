package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.testData.AlbumType;

public class ScalarEnumSchema_toString extends TestCase {{
  ScalarEnumSchema schema = new ScalarEnumSchema(Character.UnicodeScript.class);

  itMustBeEqual(schema.toString(), "Enum<UnicodeScript>");
}}
