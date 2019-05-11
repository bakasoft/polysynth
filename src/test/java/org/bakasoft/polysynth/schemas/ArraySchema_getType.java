package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.schemas.ArraySchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class ArraySchema_getType extends TestCase {{
  class StringList extends ArrayList<String> {}

  expect(new ArraySchema(int[].class).getType()).toEqual(int[].class);
  expect(new ArraySchema(String[].class).getType()).toEqual(String[].class);
  expect(new ArraySchema(Object[].class).getType()).toEqual(Object[].class);

  expect(new ArraySchema(StringList.class).getType()).toEqual(StringList.class);
  expect(new ArraySchema(List.class).getType()).toEqual(List.class);
  expect(new ArraySchema(ArrayList.class).getType()).toEqual(ArrayList.class);
  expect(new ArraySchema(Stack.class).getType()).toEqual(Stack.class);
  expect(new ArraySchema(Vector.class).getType()).toEqual(Vector.class);
}}
