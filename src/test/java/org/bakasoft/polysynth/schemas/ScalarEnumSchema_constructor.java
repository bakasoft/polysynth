package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.schemas.EnumSchema;

import java.lang.management.MemoryType;

public class  ScalarEnumSchema_constructor extends TestCase {{
  // ok...

  pass(() -> {
    new EnumSchema(MemoryType.class);
  });

  // invalid arguments...

  fail(UnexpectedTypeException.class, () -> {
    new EnumSchema(Object.class);
  });

  // missing arguments...

  fail(MissingArgumentException.class, () -> {
    new EnumSchema(null);
  });
}}
