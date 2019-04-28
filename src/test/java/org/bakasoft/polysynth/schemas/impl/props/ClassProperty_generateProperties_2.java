package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.AmbiguousPropertyException;

import java.util.Map;

public class ClassProperty_generateProperties_2 extends TestCase {{
  class Data {
    public boolean isValid() { return false; }
    public boolean getValid() { return false; }
  }

  Polysynth polysynth = new Polysynth();

  itMustFailWith(AmbiguousPropertyException.class, () -> {
    ClassProperty.generateProperties(Data.class, polysynth);
  });
}}
