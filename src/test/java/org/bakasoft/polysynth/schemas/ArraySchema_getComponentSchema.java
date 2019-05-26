package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import java.util.ArrayList;
import java.util.List;

public class ArraySchema_getComponentSchema extends TestCase {

  public static class StringList extends ArrayList<String> {}

  {
    pass("Array", () -> {
      ArraySchema schema = new ArraySchema(int[].class);
      Schema componentSchema = schema.getComponentSchema();

      expect(componentSchema).not.toBeNull();
      expect(componentSchema.getType()).toEqual(int.class);
    });

    pass("Collection", () -> {
      pass("with known component type", () -> {
        ArraySchema schema = new ArraySchema(StringList.class);
        Schema componentSchema = schema.getComponentSchema();

        expect(componentSchema).not.toBeNull();
        expect(componentSchema.getType()).toEqual(String.class);
      });
      pass("with unknown component type", () -> {
        ArraySchema schema = new ArraySchema(List.class);
        Schema componentSchema = schema.getComponentSchema();

        expect(componentSchema).not.toBeNull();
        expect(componentSchema.getType()).toEqual(Object.class);
      });
    });
  }
}
