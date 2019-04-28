package org.bakasoft.polysynth.wrappers;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.testData.Artist;

public class ObjectWrapper_Test extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectSchema schema = polysynth.getSchema(Artist.class).toObject();
  ObjectWrapper wrapper = new ObjectWrapper(new Artist(), schema);

  itMustContain(wrapper.getKeys(), "id", "name", "genres", "type");

  wrapper.setValue("id", "0001");
  wrapper.setValue("name", "Queen");
  wrapper.setValue("genres", new String[] { "rock" });

  itMustBeEqual(wrapper.getValue("id"), "0001");
  itMustBeEqual(wrapper.getValue("name"), "Queen");
  itMustIterate(wrapper.getValue("genres"), "rock");
  itMustBeEqual(wrapper.getValue("type"), "artist");

  Object instance = wrapper.getInstance();

  itMustBeInstanceOf(instance, Artist.class);

  Artist artist = (Artist)instance;

  itMustBeEqual(artist.getId(), "0001");
  itMustBeEqual(artist.getName(), "Queen");
  itMustBeEqual(artist.getType(), "artist");
  itMustIterate(artist.getGenres(), "rock");
}}
