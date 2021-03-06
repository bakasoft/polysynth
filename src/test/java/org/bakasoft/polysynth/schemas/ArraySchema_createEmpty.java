package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import java.util.List;

public class ArraySchema_createEmpty extends TestCase {{

  pass("Array", () -> {
    ArraySchema schema = new ArraySchema(Boolean[].class);
    Object instance = schema.createEmpty();

    expect(instance).toBeInstanceOf(Boolean[].class);
    expect(instance).toBeEmpty();
  });

  pass("Collection", () -> {
    ArraySchema schema = new ArraySchema(List.class);
    Object instance = schema.createEmpty();

    expect(instance).toBeInstanceOf(List.class);
    expect(instance).toBeEmpty();
  });

}}
