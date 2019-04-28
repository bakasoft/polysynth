package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectClassSchema_toString extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectClassSchema schema = new ObjectClassSchema(polysynth, Artist.class);
  itMustBeEqual(schema.toString(), "Object<Artist>");
}}
