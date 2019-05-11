package org.bakasoft.polysynth.schemas;

import org.bakasoft.beat.annotations.Property;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.testData.Artist;

import java.util.HashMap;

public class ObjectSchema_createWith extends TestCase {

  public static class Artist {
    public final String id;
    public String name;
    public String[] genres;

    public final String type = "artist";

    public Artist(@Property("id") String id) {
      this.id = id;
    }
  }

  {
    ObjectSchema schema = new ObjectSchema(Artist.class);

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
  }

}
