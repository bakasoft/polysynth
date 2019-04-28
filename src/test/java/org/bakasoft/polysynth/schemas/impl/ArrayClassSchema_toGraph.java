package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.graph.GraphArray;
import org.bakasoft.polysynth.graph.GraphValue;

public class ArrayClassSchema_toGraph extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayClassSchema schema = new ArrayClassSchema(polysynth, Integer[].class);

  Integer[] array = { 314, 159, 265 };

  GraphValue graphValue = schema.toGraph(array, polysynth);

  itMustBeInstanceOf(graphValue, GraphArray.class);

  GraphArray graphArray = (GraphArray)graphValue;
  GraphValue[] graphItems = graphArray.getValues();

  itMustBeEqual(graphItems.length, 3);
  itMustBeEqual(graphArray.toJson(), "[314,159,265]");
}}
