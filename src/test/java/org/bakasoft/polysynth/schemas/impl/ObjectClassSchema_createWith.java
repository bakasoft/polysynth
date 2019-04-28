package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.testData.Artist;

import java.util.HashMap;

public class ObjectClassSchema_createWith extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectClassSchema schema = new ObjectClassSchema(polysynth, Artist.class);

  HashMap<String, Object> initialValues = new HashMap<>();
  initialValues.put("id", "0001");
  initialValues.put("name", "Save Ferris");
  initialValues.put("genres", new String[]{"ska punk"});

  Object instance = schema.createWith(initialValues);

  itMustBeInstanceOf(instance, Artist.class);
  itMustBeEqual(schema.get(instance, "id"), "0001");
  itMustBeEqual(schema.get(instance, "name"), "Save Ferris");
  itMustIterate(schema.get(instance, "genres"), "ska punk");
  itMustBeEqual(schema.get(instance, "type"), "artist");
}}
