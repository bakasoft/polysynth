package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

import java.util.List;

public class ArraySchema_toArray extends TestCase {{
  pass("should convert the Array schema to Array", () -> {
    ArraySchema schema = new ArraySchema(Double[].class);

    expect(schema.toArray()).toBe(schema);
  });
  pass("should convert the Collection schema to Array", () -> {
    ArraySchema schema = new ArraySchema(List.class);

    expect(schema.toArray()).toBe(schema);
  });
}}
