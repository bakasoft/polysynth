package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;

import java.util.Arrays;
import java.util.List;

public class ArrayListSchema_createEmpty extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayListSchema schema = new ArrayListSchema(polysynth, List.class);

  Object instance = schema.createEmpty();

  itMustBeInstanceOf(instance, List.class);

  List<?> list = (List<?>)instance;

  itMustBeEqual(list.isEmpty(), true);
}}
