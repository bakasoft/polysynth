package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

public class ScalarEnumSchema_toArray_toObject_toScalar extends TestCase {{
  ScalarEnumSchema schema = new ScalarEnumSchema(Thread.State.class);

  itMustBeSame(schema.toScalar(), schema);
  itMustFailWith(UnexpectedTypeException.class, schema::toArray);
  itMustFailWith(UnexpectedTypeException.class, schema::toObject);
}}
