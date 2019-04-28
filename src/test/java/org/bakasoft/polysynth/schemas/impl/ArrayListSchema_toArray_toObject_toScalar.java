package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

public class ArrayListSchema_toArray_toObject_toScalar extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayListSchema schema = new ArrayListSchema(polysynth, Float[].class);

  itMustBeSame(schema.toArray(), schema);
  itMustFailWith(UnexpectedTypeException.class, schema::toObject);
  itMustFailWith(UnexpectedTypeException.class, schema::toScalar);
}}
