package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.beat.exceptions.BeatException;
import org.bakasoft.polysynth.Polysynth;

import java.util.ArrayList;
import java.util.List;

public class ArraySchema_add extends TestCase {

  public static class StringList extends ArrayList<String> {}

  {
    pass("Array", () -> {
      fail("should fail because array size can't be changed", () -> {
        Polysynth polysynth = new Polysynth();
        ArraySchema schema = new ArraySchema(polysynth, String[].class);
        String[] instance = { "a", "b", "c" };

        schema.add(instance, "d");
      });
    });

    pass("Collection", () -> {
      pass("should add validate items when component type is known", () -> {
        Polysynth polysynth = new Polysynth();
        ArraySchema schema = new ArraySchema(polysynth, StringList.class);

        StringList instance = new StringList();

        schema.add(instance, "a");
        schema.add(instance, "b");
        schema.add(instance, "c");

        expect(instance).toIterate("a", "b", "c");
      });

      fail("should fail with invalid items when component type is known", BeatException.class, () -> {
        Polysynth polysynth = new Polysynth();

        ArraySchema schema = new ArraySchema(polysynth, StringList.class);

        StringList instance = new StringList();

        schema.add(instance, 100);
      });


      pass("should not validate items when component type is unknown", () -> {
        Polysynth polysynth = new Polysynth();
        ArraySchema schema = new ArraySchema(polysynth, List.class);

        ArrayList<?> instance = new ArrayList<>();

        schema.add(instance, 10);
        schema.add(instance, true);
        schema.add(instance, "x");

        expect(instance).toIterate(10, true, "x");
      });

      fail("should fail with null instances", BeatException.class, () -> {
        Polysynth polysynth = new Polysynth();

        ArraySchema schema = new ArraySchema(polysynth, List.class);

        schema.add(null, "...");
      });
    });
  }
}
