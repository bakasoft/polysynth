package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectSchema_createEmpty extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = new ObjectSchema(polysynth, Artist.class);

  Object empty = schema.createEmpty();

  expect(empty).toBeInstanceOf(Artist.class);
  expect(schema.get(empty, "id")).toBeNull();
  expect(schema.get(empty, "name")).toBeNull();
  expect(schema.get(empty, "genres")).toBeNull();
  expect(schema.get(empty, "type")).toEqual("artist");
}}
