package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.schemas.ArraySchema;

import java.util.*;

public class ArraySchema_list extends TestCase {{

  pass("should list the items of the Collection", () -> {
    ArraySchema schema = new ArraySchema(List.class);
    List<?> result = schema.list(Arrays.asList("abc", 100, null, true));

    expect(result).toIterate("abc", 100, null, true);
  });

  pass("should list the items of the Array", () -> {
    ArraySchema schema = new ArraySchema(Object[].class);
    List<?> result = schema.list(new Object[] {"abc", 100, null, true });

    expect(result).toIterate("abc", 100, null, true);
  });

  fail("should fail with null instance", () -> {
    ArraySchema schema = new ArraySchema(List.class);

    schema.list(null);
  });

  fail("should fail with invalid instance", () -> {
    ArraySchema schema = new ArraySchema(Vector.class);

    schema.list(new ArrayList<>());
  });
}}
