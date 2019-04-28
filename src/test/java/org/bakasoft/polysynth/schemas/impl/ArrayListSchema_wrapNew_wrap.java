package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.wrappers.ArrayWrapper;

import java.util.ArrayList;
import java.util.List;

public class ArrayListSchema_wrapNew_wrap extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayListSchema schema = new ArrayListSchema(polysynth, List.class);

  // wrap new list
  {
    ArrayList<String> list = new ArrayList<>();
    ArrayWrapper wrapper = schema.wrap(list);

    itMustBeSame(wrapper.getInstance(), list);
    itMustBeSame(wrapper.getSchema(), schema);
  }

  // wrap new empty list
  {
    ArrayWrapper wrapper = schema.wrapNew();
    Object instance = wrapper.getInstance();

    itMustBeInstanceOf(instance, List.class);
  }

  // wrap new initialized list
  {
    ArrayList<String> data = new ArrayList<>();
    data.add("Natural Mystic");

    ArrayWrapper wrapper = schema.wrapNew(data);
    Object instance = wrapper.getInstance();

    itMustBeInstanceOf(instance, List.class);

    List<?> list = (List<?>)instance;

    itMustIterate(list, "Natural Mystic");
  }
}}
