package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.AmbiguousPropertyException;

public class ClassProperty_generateProperties_4 extends TestCase {{
  class Data {
    public boolean valid;
    public boolean getValid() { return false; }
  }

  Polysynth polysynth = new Polysynth();

  itMustFailWith(AmbiguousPropertyException.class, () -> {
    ClassProperty.generateProperties(Data.class, polysynth);
  });
}}
