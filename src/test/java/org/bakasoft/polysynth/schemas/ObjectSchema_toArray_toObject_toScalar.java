package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.schemas.ObjectSchema;

public class ObjectSchema_toArray_toObject_toScalar extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = new ObjectSchema(polysynth, Double[].class);

  expect(schema.toObject()).toBe(schema);
  fail(UnexpectedTypeException.class, schema::toArray);
}}
