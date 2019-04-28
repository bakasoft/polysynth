package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class ArrayListSchema_getType extends TestCase {{
  Polysynth polysynth = new Polysynth();

  class StringList extends ArrayList<String> {}

  itMustBeEqual(new ArrayListSchema(polysynth, StringList.class).getType(), StringList.class);
  itMustBeEqual(new ArrayListSchema(polysynth, List.class).getType(), List.class);
  itMustBeEqual(new ArrayListSchema(polysynth, ArrayList.class).getType(), ArrayList.class);
  itMustBeEqual(new ArrayListSchema(polysynth, Stack.class).getType(), Stack.class);
  itMustBeEqual(new ArrayListSchema(polysynth, Vector.class).getType(), Vector.class);
}}
