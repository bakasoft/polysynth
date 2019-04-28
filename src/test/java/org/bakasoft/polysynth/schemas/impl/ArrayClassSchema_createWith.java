package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

import java.util.Arrays;

public class ArrayClassSchema_createWith extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayClassSchema schema = new ArrayClassSchema(polysynth, String[].class);

  Object instance = schema.createWith(Arrays.asList("a", "b", "c"));

  itMustBeInstanceOf(instance, String[].class);
  itMustIterate(instance, "a", "b", "c");

  itMustFailWith(UnexpectedTypeException.class, () -> {
    schema.createWith(Arrays.asList(1, 2, 3));
  });
}}
