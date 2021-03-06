package org.bakasoft.polysynth.util;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.polysynth.errors.ObjectCreationException;

import java.util.function.Supplier;

public class ReflectionHelper_makeObjectCreator extends TestCase {
  static class CustomType {
    public int id;
  }

  static class PrivateType {
    public int id;
    private PrivateType() {}
  }

  {
    Supplier<Object> supplier = ReflectionHelper.makeObjectCreator(CustomType.class);

    Object instance = supplier.get();

    expect(instance).toBeInstanceOf(CustomType.class);
  }

  {
    Supplier<Object> supplier = ReflectionHelper.makeObjectCreator(PrivateType.class);

    fail(ObjectCreationException.class, supplier::get);
  }
}
