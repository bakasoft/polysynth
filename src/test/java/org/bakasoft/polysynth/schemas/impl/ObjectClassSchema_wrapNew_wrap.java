package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.testData.Artist;
import org.bakasoft.polysynth.wrappers.ObjectWrapper;

import java.util.HashMap;

public class ObjectClassSchema_wrapNew_wrap extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ObjectClassSchema schema = new ObjectClassSchema(polysynth, Artist.class);

  {
    Artist artist = new Artist();
    ObjectWrapper wrapper = schema.wrap(artist);

    itMustBeSame(wrapper.getInstance(), artist);
    itMustBeSame(wrapper.getSchema(), schema);
  }

  {
    ObjectWrapper wrapper = schema.wrapNew();
    Object instance = wrapper.getInstance();

    itMustBeInstanceOf(instance, Artist.class);
  }

  {
    HashMap<String, Object> data = new HashMap<>();
    data.put("name", "Bob Marley & The Wailers");

    ObjectWrapper wrapper = schema.wrapNew(data);
    Object instance = wrapper.getInstance();

    itMustBeInstanceOf(instance, Artist.class);

    Artist artist = (Artist)instance;

    itMustBeEqual(artist.getName(), "Bob Marley & The Wailers");
  }
}}
