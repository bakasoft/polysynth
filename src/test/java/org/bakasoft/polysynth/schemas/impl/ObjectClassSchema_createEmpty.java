package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectClassSchema_createEmpty extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectClassSchema schema = new ObjectClassSchema(polysynth, Artist.class);

  Object empty = schema.createEmpty();

  itMustBeInstanceOf(empty, Artist.class);
  itMustBeEqual(schema.get(empty, "id"), null);
  itMustBeEqual(schema.get(empty, "name"), null);
  itMustBeEqual(schema.get(empty, "genres"), null);
  itMustBeEqual(schema.get(empty, "type"), "artist");
}}
