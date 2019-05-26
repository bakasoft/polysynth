package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphNumber;

public class Scalar_int_class extends TestCase {{
  Scalar_int schema = new Scalar_int();

  expect(schema.getType()).toEqual(int.class);
  expect(schema.toString()).toEqual("Scalar<int>");
  expect(schema.toGraph(10)).toBeInstanceOf(GraphNumber.class);

  expect(schema.convert(10)).toEqual(10);
  expect(schema.convert(10L)).toEqual(10);
  expect(schema.convert("10")).toEqual(10);
  fail(ConversionException.class, () -> schema.convert(null));
  fail(ConversionException.class, () -> schema.convert(true));
  fail(ConversionException.class, () -> schema.convert(false));
  fail(ConversionException.class, () -> schema.convert("10A"));
  fail(ConversionException.class, () -> schema.convert("1,1"));
  fail(ConversionException.class, () -> schema.convert(""));
  fail(ConversionException.class, () -> schema.convert(new Object()));
}}
