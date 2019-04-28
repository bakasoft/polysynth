package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.MissingArgumentException;

import java.util.ArrayList;

public class ArrayListSchema_add extends TestCase {{
  Polysynth polysynth = new Polysynth();

  class StringList extends ArrayList<String> {}

  ArrayListSchema schema = new ArrayListSchema(polysynth, StringList.class);

  StringList instance = new StringList();

  schema.add(instance, "a");
  schema.add(instance, "b");
  schema.add(instance, "c");

  itMustIterate(instance, "a", "b", "c");

  // TODO following line should generate an error
  schema.add(instance, 100);

  // add to a null instance...

  itMustFailWith(MissingArgumentException.class, () -> {
    schema.add(null, "?");
  });

  // TODO when the getItemSchema is implemented, create a test when it has a value and when it has not.
}}
