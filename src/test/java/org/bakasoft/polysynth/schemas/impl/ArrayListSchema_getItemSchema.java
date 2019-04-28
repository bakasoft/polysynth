package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;

import java.util.ArrayList;
import java.util.Vector;

public class ArrayListSchema_getItemSchema extends TestCase {{
  Polysynth polysynth = new Polysynth();

  class StringList extends ArrayList<String> {}

  ArrayListSchema schema = new ArrayListSchema(polysynth, StringList.class);

  itMustBeEqual(schema.getItemSchema(), null);
}}
