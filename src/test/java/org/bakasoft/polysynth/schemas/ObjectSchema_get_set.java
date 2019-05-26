package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.beat.exceptions.BeatException;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.testData.Artist;

import java.util.HashMap;

public class ObjectSchema_get_set extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = new ObjectSchema(polysynth, Artist.class);

  HashMap<String, Object> initialValues = new HashMap<>();
  initialValues.put("id", "0001");
  initialValues.put("name", "Save Ferris");
  initialValues.put("genres", new String[]{"ska punk"});

  Object instance = schema.createWith(initialValues);

  expect(instance).toBeInstanceOf(Artist.class);
  expect(schema.get(instance, "id")).toEqual("0001");
  expect(schema.get(instance, "name")).toEqual("Save Ferris");
  expect(schema.get(instance, "genres")).toIterate("ska punk");
  expect(schema.get(instance, "type")).toEqual("artist");

  schema.set(instance, "genres", new String[]{"ska", "punk"});

  expect(schema.get(instance, "genres")).toIterate("ska", "punk");

  fail(BeatException.class, () -> {
    schema.set(instance, "?", null);
  });

  fail(BeatException.class, () -> {
    schema.get(instance, "?");
  });
}}
