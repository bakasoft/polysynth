package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
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
