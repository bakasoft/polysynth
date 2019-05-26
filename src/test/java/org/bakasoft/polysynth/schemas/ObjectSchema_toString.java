package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectSchema_toString extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = new ObjectSchema(polysynth, Artist.class);

  expect(schema.toString()).toEqual("Object<org.bakasoft.polysynth.testData.Artist>");
}}
