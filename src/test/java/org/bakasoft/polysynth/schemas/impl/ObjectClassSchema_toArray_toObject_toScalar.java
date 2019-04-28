package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

public class ObjectClassSchema_toArray_toObject_toScalar extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectClassSchema schema = new ObjectClassSchema(polysynth, Double[].class);

  itMustBeSame(schema.toObject(), schema);
  itMustFailWith(UnexpectedTypeException.class, schema::toArray);
  itMustFailWith(UnexpectedTypeException.class, schema::toScalar);
}}
