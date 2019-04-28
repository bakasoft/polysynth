package org.bakasoft.polysynth.wrappers;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.ArrayMutationException;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.testData.Artist;
import org.bakasoft.polysynth.testData.TestData;

public class ArrayWrapper_StaticTest extends TestCase {{
  TestData data = new TestData();
  Polysynth polysynth = new Polysynth();
  ArraySchema schema = polysynth.getSchema(Artist[].class).toArray();
  ArrayWrapper wrapper = new ArrayWrapper(new Artist[] { data.queen, data.deepPurple, data.mrBig }, schema);

  itMustIterate(wrapper.getItems(), data.queen, data.deepPurple, data.mrBig);

  itMustFailWith(ArrayMutationException.class, () -> {
    wrapper.addItem(data.motorhead);
  });

  // TODO test missing mutation methods

  Object instance = wrapper.getInstance();

  itMustBeInstanceOf(instance, Artist[].class);

  Artist[] artists = (Artist[])instance;

  itMustIterate(artists, data.queen, data.deepPurple, data.mrBig);
}}
