package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.UnknownPropertyException;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectSchema_isReadable extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = new ObjectSchema(polysynth, Artist.class);

  expect(schema.isReadable("id")).toBeTrue();
  expect(schema.isReadable("name")).toBeTrue();
  expect(schema.isReadable("genres")).toBeTrue();
  expect(schema.isReadable("type")).toBeTrue();

  fail(UnknownPropertyException.class, () -> {
    schema.isReadable("?");
  });
}}
