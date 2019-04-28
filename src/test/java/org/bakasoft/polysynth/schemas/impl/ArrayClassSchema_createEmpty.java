package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;

public class ArrayClassSchema_createEmpty extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayClassSchema schema = new ArrayClassSchema(polysynth, Boolean[].class);

  Object instance = schema.createEmpty();

  itMustBeInstanceOf(instance, Boolean[].class);
  itMustBeEqual(schema.list(instance).size(), 0);
}}
