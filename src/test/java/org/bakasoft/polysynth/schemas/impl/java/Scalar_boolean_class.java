package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphBoolean;

public class Scalar_boolean_class extends TestCase {{
  Scalar_boolean schema = new Scalar_boolean();

  expect(schema.getType()).toEqual(boolean.class);
  expect(schema.toString()).toEqual("Scalar<boolean>");
  expect(schema.toGraph(true)).toBeInstanceOf(GraphBoolean.class);

  expect(schema.convert(true)).toBeTrue();
  expect(schema.convert(false)).toBeFalse();
  expect(schema.convert("true")).toBeTrue();
  expect(schema.convert("false")).toBeFalse();
  expect(schema.convert(Boolean.TRUE)).toBeTrue();
  expect(schema.convert(Boolean.FALSE)).toBeFalse();
  fail(ConversionException.class, () -> schema.convert(null));
  fail(ConversionException.class, () -> schema.convert(0));
  fail(ConversionException.class, () -> schema.convert(1));
  fail(ConversionException.class, () -> schema.convert("yes"));
  fail(ConversionException.class, () -> schema.convert("no"));
  fail(ConversionException.class, () -> schema.convert(""));
  fail(ConversionException.class, () -> schema.convert(new Object()));
}}
