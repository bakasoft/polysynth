package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListSchema_createWith extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayListSchema schema = new ArrayListSchema(polysynth, List.class);

  Object instance = schema.createWith(Arrays.asList("a", 1, true));

  itMustBeInstanceOf(instance, List.class);
  itMustIterate(instance, "a", 1, true);
}}
