package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.schemas.EnumSchema;

public class ScalarEnumSchema_toArray_toObject_toScalar extends TestCase {{
  EnumSchema schema = new EnumSchema(Thread.State.class);

  fail(UnexpectedTypeException.class, schema::toArray);
  fail(UnexpectedTypeException.class, schema::toObject);
}}
