package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;

public class ArrayClassSchema_toString extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayClassSchema schema = new ArrayClassSchema(polysynth, String[].class);

  itMustBeEqual(schema.toString(), "Array<String>");
}}
