package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.Polysynth;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class ArraySchema_toString extends TestCase {{
  pass("array", () -> {
    Polysynth polysynth = new Polysynth();
    ArraySchema schema = new ArraySchema(polysynth, String[].class);

    expect(schema.toString()).toEqual("Array<String>");
  });

  pass("collection", () -> {
    Polysynth polysynth = new Polysynth();

    class StringList extends ArrayList<String> {}

    expect(new ArraySchema(polysynth, StringList.class).toString()).toEqual("Array<String>");
    expect(new ArraySchema(polysynth, List.class).toString()).toEqual("Array<Object>");
    expect(new ArraySchema(polysynth, ArrayList.class).toString()).toEqual("Array<Object>");
    expect(new ArraySchema(polysynth, Stack.class).toString()).toEqual("Array<Object>");
    expect(new ArraySchema(polysynth, Vector.class).toString()).toEqual("Array<Object>");
  });
}}
