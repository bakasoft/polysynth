package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.graph.GraphString;
import org.bakasoft.polysynth.graph.GraphValue;

import java.math.RoundingMode;

public class ScalarEnumSchema_toGraph extends TestCase {{
  EnumSchema schema = new EnumSchema(RoundingMode.class);

  GraphValue graphValue = schema.toGraph(RoundingMode.HALF_EVEN);

  // enums are represented by strings...
  expect(graphValue instanceof GraphString).toBeTrue();

  GraphString graphString = (GraphString)graphValue;

  // ...using the name of the enum
  expect(graphString.getValue()).toEqual("HALF_EVEN");

  // null instance produces null graph
  expect(schema.toGraph(null)).toBeNull();
}}
