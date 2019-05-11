package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.graph.GraphArray;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ArraySchema;

import java.util.ArrayList;
import java.util.List;

public class ArraySchema_toGraph extends TestCase {{
  pass("Array", () -> {
    Polysynth polysynth = new Polysynth();
    ArraySchema schema = new ArraySchema(polysynth, Integer[].class);

    Integer[] array = { 314, 159, 265 };

    GraphValue graphValue = schema.toGraph(array);

    expect(graphValue).toBeInstanceOf(GraphArray.class);

    GraphArray graphArray = (GraphArray)graphValue;
    GraphValue[] graphItems = graphArray.getValues();

    expect(graphItems.length).toEqual(3);
    expect(graphArray.toJson()).toEqual("[314,159,265]");
  });

  pass("Collection", () -> {
    Polysynth polysynth = new Polysynth();
    ArraySchema schema = new ArraySchema(polysynth, List.class);
    ArrayList<Object> instance = new ArrayList<>();
    instance.add("abc");
    instance.add(123);
    instance.add(10.5);

    GraphValue graphValue = schema.toGraph(instance);

    expect(graphValue).toBeInstanceOf(GraphArray.class);

    GraphArray graphArray = (GraphArray)graphValue;
    GraphValue[] graphItems = graphArray.getValues();

    expect(graphItems.length).toEqual(3);
    expect(graphArray.toJson()).toEqual("[\"abc\",123,10.5]");
  });
}}
