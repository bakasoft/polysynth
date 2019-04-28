package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class ArrayListSchema_list extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayListSchema schema = new ArrayListSchema(polysynth, Vector.class);

  Vector<Object> instance = new Vector<>();
  instance.add("abc");
  instance.add(100);
  instance.add(null);
  instance.add(true);

  List<Object> list = schema.list(instance);

  itMustIterate(list, "abc", 100, null, true);

  // with invalid arguments...

  itMustFailWith(MissingArgumentException.class, () -> {
    schema.list(null);
  });

  itMustFailWith(UnexpectedTypeException.class, () -> {
    schema.list(new ArrayList<>());
  });
}}
