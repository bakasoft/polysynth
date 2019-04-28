package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.ScalarSchema;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectClassSchema_getSchema_getType extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectClassSchema schema = new ObjectClassSchema(polysynth, Artist.class);

  itMustBeEqual(schema.getSchema("?"), null);
  itMustBeInstanceOf(schema.getSchema("id"), ScalarSchema.class);
  itMustBeInstanceOf(schema.getSchema("name"), ScalarSchema.class);
  itMustBeInstanceOf(schema.getSchema("genres"), ArraySchema.class);
  itMustBeInstanceOf(schema.getSchema("type"), ScalarSchema.class);

  itMustBeEqual(schema.getType("?"), null);
  itMustBeEqual(schema.getType("id"), String.class);
  itMustBeEqual(schema.getType("name"), String.class);
  itMustBeEqual(schema.getType("genres"), String[].class);
  itMustBeEqual(schema.getType("type"), String.class);
}}
