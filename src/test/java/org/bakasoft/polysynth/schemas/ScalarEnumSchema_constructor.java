package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

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
