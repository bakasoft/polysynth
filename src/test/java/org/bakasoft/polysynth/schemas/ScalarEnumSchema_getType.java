package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import java.net.StandardProtocolFamily;

public class ScalarEnumSchema_getType extends TestCase {{
  EnumSchema schema = new EnumSchema(StandardProtocolFamily.class);

  expect(schema.getType()).toEqual(StandardProtocolFamily.class);
}}
