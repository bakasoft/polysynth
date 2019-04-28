package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.graph.GraphArray;
import org.bakasoft.polysynth.graph.GraphValue;

import java.util.ArrayList;
import java.util.List;

public class ArrayListSchema_toGraph extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayListSchema schema = new ArrayListSchema(polysynth, List.class);
  ArrayList<Object> instance = new ArrayList<>();
  instance.add("abc");
  instance.add(123);
  instance.add(10.5);

  GraphValue graphValue = schema.toGraph(instance, polysynth);

  itMustBeInstanceOf(graphValue, GraphArray.class);

  GraphArray graphArray = (GraphArray)graphValue;
  GraphValue[] graphItems = graphArray.getValues();

  itMustBeEqual(graphItems.length, 3);
  itMustBeEqual(graphArray.toJson(), "[\"abc\",123,10.5]");

}}
