package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.UnknownPropertyException;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectSchema_isWritable extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = new ObjectSchema(polysynth, Artist.class);

  expect(schema.isWritable("id")).toBeTrue();
  expect(schema.isWritable("name")).toBeTrue();
  expect(schema.isWritable("genres")).toBeTrue();
  expect(schema.isWritable("type")).toBeFalse();

  fail(UnknownPropertyException.class, () -> {
    schema.isWritable("?");
  });
}}
