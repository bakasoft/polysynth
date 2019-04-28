package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;

import java.util.Map;

public class ClassProperty_generateProperties_1 extends TestCase {
  static class Data {
    public static String fieldStatic;
    public String normalField;
  }

  // normal & static properties
  {
    Polysynth polysynth = new Polysynth();
    Map<String, ClassProperty> props = ClassProperty.generateProperties(Data.class, polysynth);

    itMustBeEqual(props.containsKey("normalField"), true);
    itMustBeEqual(props.containsKey("fieldStatic"), false);
  }
}
