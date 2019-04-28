package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.schemas.ScalarSchema;

public class ArrayClassSchema_getItemSchema extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayClassSchema schema = new ArrayClassSchema(polysynth, int[].class);

  itMustBeInstanceOf(schema.getItemSchema(), ScalarSchema.class);
  itMustBeEqual(schema.getItemSchema().getType(), int.class);
}}
