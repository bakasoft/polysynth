package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

public class ArrayClassSchema_constructor extends TestCase {{
  Polysynth polysynth = new Polysynth();

  // valid construction
  new ArrayClassSchema(polysynth, Object[].class);

  itMustFailWith(UnexpectedTypeException.class, () -> {
    // invalid construction
    new ArrayClassSchema(polysynth, Object.class);
  });
}}
