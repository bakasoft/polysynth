package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

import java.lang.management.MemoryType;

public class  ScalarEnumSchema_constructor extends TestCase {{
  // ok...

  new ScalarEnumSchema(MemoryType.class);

  // invalid arguments...

  itMustFailWith(UnexpectedTypeException.class, () -> {
    new ScalarEnumSchema(Object.class);
  });

  // missing arguments...

  itMustFailWith(MissingArgumentException.class, () -> {
    new ScalarEnumSchema(null);
  });
}}
