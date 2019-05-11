package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectSchema_getKeys extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = new ObjectSchema(polysynth, Artist.class);

  expect(schema.getKeys()).toContain("id", "name", "genres", "type");
}}
