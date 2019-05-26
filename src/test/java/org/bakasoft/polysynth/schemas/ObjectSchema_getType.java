package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectSchema_getType extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = new ObjectSchema(polysynth, Artist.class);

  expect(schema.getType()).toEqual(Artist.class);
}}
