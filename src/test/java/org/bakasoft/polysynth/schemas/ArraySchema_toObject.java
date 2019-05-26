package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import java.util.List;

public class ArraySchema_toObject extends TestCase {{
  fail("should fail converting the Array schema to Object", () -> {
    ArraySchema schema = new ArraySchema(Double[].class);

    // following line should fail
    schema.toObject();
  });
  fail("should fail converting the Collection schema to Object", () -> {
    ArraySchema schema = new ArraySchema(List.class);

    // following line should fail
    schema.toObject();
  });
}}
