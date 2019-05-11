package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.schemas.ArraySchema;

import java.util.Arrays;
import java.util.List;

public class ArraySchema_createWith extends TestCase {{
  pass("Array", () -> {
    pass("with valid items", () -> {
      ArraySchema schema = new ArraySchema(String[].class);
      Object instance = schema.createWith(Arrays.asList("a", "b", "c"));

      expect(instance)
          .toBeInstanceOf(String[].class)
          .toIterate("a", "b", "c");
    });

    fail("with invalid items", () -> {
      ArraySchema schema = new ArraySchema(Exception[].class);

      // following line should fail
      schema.createWith(Arrays.asList(1, 2, 3));
    });
  });

  pass("Collection", () -> {
    ArraySchema schema = new ArraySchema(List.class);
    Object instance = schema.createWith(Arrays.asList("a", 1, true));

    expect(instance)
        .toBeInstanceOf(List.class)
        .toIterate("a", 1, true);

    // TODO add cases for valid and invalid items
  });
}}
