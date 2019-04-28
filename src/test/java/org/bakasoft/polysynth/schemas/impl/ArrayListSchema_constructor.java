package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.MissingArgumentException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class ArrayListSchema_constructor extends TestCase {{
  Polysynth polysynth = new Polysynth();

  // ok...

  class CustomList extends ArrayList<String> {}

  new ArrayListSchema(polysynth, CustomList.class);
  new ArrayListSchema(polysynth, ArrayList.class);
  new ArrayListSchema(polysynth, LinkedList.class);
  new ArrayListSchema(polysynth, Stack.class);
  new ArrayListSchema(polysynth, Vector.class);

  // invalid arguments...

  itMustFailWith(MissingArgumentException.class, () -> {
    new ArrayListSchema(null, null);
  });

  // missing arguments...

  itMustFailWith(MissingArgumentException.class, () -> {
    new ArrayListSchema(null, null);
  });

  itMustFailWith(MissingArgumentException.class, () -> {
    new ArrayListSchema(polysynth, null);
  });

  itMustFailWith(MissingArgumentException.class, () -> {
    new ArrayListSchema(null, ArrayList.class);
  });
}}
