package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

public class ArrayClassSchema_toArray_toObject_toScalar extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayClassSchema schema = new ArrayClassSchema(polysynth, Double[].class);

  itMustBeSame(schema.toArray(), schema);
  itMustFailWith(UnexpectedTypeException.class, schema::toObject);
  itMustFailWith(UnexpectedTypeException.class, schema::toScalar);
}}
