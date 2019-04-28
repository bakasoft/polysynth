package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnknownPropertyException;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectClassSchema_isWriteOnly extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectClassSchema schema = new ObjectClassSchema(polysynth, Artist.class);

  itMustBeEqual(schema.isWriteOnly("id"), false);
  itMustBeEqual(schema.isWriteOnly("name"), false);
  itMustBeEqual(schema.isWriteOnly("genres"), false);
  itMustBeEqual(schema.isWriteOnly("type"), false);

  itMustFailWith(UnknownPropertyException.class, () -> {
    schema.isWriteOnly("?");
  });
}}
