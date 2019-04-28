package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.graph.GraphString;
import org.bakasoft.polysynth.graph.GraphValue;

import java.math.RoundingMode;

public class ScalarEnumSchema_toGraph extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ScalarEnumSchema schema = new ScalarEnumSchema(RoundingMode.class);

  GraphValue graphValue = schema.toGraph(RoundingMode.HALF_EVEN, polysynth);

  // enums are represented by strings...
  itMustBeInstanceOf(graphValue, GraphString.class);

  GraphString graphString = (GraphString)graphValue;

  // ...using the name of the enum
  itMustBeEqual(graphString.getValue(), "HALF_EVEN");

  // null instance produces null graph
  itMustBeEqual(schema.toGraph(null, polysynth), null);
}}
