package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnknownPropertyException;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectClassSchema_isReadOnly extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectClassSchema schema = new ObjectClassSchema(polysynth, Artist.class);

  itMustBeEqual(schema.isReadOnly("id"), false);
  itMustBeEqual(schema.isReadOnly("name"), false);
  itMustBeEqual(schema.isReadOnly("genres"), false);
  itMustBeEqual(schema.isReadOnly("type"), true);

  itMustFailWith(UnknownPropertyException.class, () -> {
    schema.isReadOnly("?");
  });
}}
