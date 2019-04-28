package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.testData.AlbumType;

import java.net.StandardProtocolFamily;

public class ScalarEnumSchema_getType extends TestCase {{
  ScalarEnumSchema schema = new ScalarEnumSchema(StandardProtocolFamily.class);

  itMustBeEqual(schema.getType(), StandardProtocolFamily.class);
}}
