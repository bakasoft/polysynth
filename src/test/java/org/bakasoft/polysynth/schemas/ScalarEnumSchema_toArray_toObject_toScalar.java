package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

public class ScalarEnumSchema_toArray_toObject_toScalar extends TestCase {{
  EnumSchema schema = new EnumSchema(Thread.State.class);

  fail(UnexpectedTypeException.class, schema::toArray);
  fail(UnexpectedTypeException.class, schema::toObject);
}}
