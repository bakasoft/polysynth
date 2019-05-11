package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.graph.GraphObject;
import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.testData.Artist;

import java.util.HashMap;

public class ObjectSchema_toGraph extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = new ObjectSchema(polysynth, Artist.class);

  HashMap<String, Object> initialValues = new HashMap<>();
  initialValues.put("id", "0001");
  initialValues.put("name", "Save Ferris");
  initialValues.put("genres", new String[]{"ska punk"});

  Object instance = schema.createWith(initialValues);

  expect(instance instanceof Artist).toBeTrue();
  expect(schema.get(instance, "id")).toEqual("0001");
  expect(schema.get(instance, "name")).toEqual("Save Ferris");
  expect(schema.get(instance, "genres")).toIterate("ska punk");
  expect(schema.get(instance, "type")).toEqual("artist");

  GraphValue graphValue = schema.toGraph(instance);

  expect(graphValue instanceof GraphObject).toBeTrue();
}}
