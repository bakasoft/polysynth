package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectSchema_getSchema_getType extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = new ObjectSchema(polysynth, Artist.class);

  expect(schema.getSchema("?")).toBeNull();
  expect(schema.getSchema("id")).toBeInstanceOf(Schema.class);
  expect(schema.getSchema("name")).toBeInstanceOf(Schema.class);
  expect(schema.getSchema("genres")).toBeInstanceOf(ArraySchema.class);
  expect(schema.getSchema("type")).toBeInstanceOf(Schema.class);

  expect(schema.getType("?")).toBeNull();
  expect(schema.getType("id")).toEqual(String.class);
  expect(schema.getType("name")).toEqual(String.class);
  expect(schema.getType("genres")).toEqual(String[].class);
  expect(schema.getType("type")).toEqual(String.class);
}}
