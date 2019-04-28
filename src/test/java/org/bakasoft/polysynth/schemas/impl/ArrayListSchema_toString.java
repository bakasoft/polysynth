package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class ArrayListSchema_toString extends TestCase {{
  Polysynth polysynth = new Polysynth();

  class StringList extends ArrayList<String> {}

  itMustBeEqual(new ArrayListSchema(polysynth, StringList.class).toString(),  "Array<StringList>");
  itMustBeEqual(new ArrayListSchema(polysynth, List.class).toString(),  "Array<List>");
  itMustBeEqual(new ArrayListSchema(polysynth, ArrayList.class).toString(),  "Array<ArrayList>");
  itMustBeEqual(new ArrayListSchema(polysynth, Stack.class).toString(),  "Array<Stack>");
  itMustBeEqual(new ArrayListSchema(polysynth, Vector.class).toString(),  "Array<Vector>");
}}
