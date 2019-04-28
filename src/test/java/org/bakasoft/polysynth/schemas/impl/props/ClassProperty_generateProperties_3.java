package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.AmbiguousPropertyException;

public class ClassProperty_generateProperties_3 extends TestCase {{
  class Data {
    public void setValid(boolean value) {}
    public void setvalid(boolean value) {}
  }

  Polysynth polysynth = new Polysynth();

  itMustFailWith(AmbiguousPropertyException.class, () -> {
    ClassProperty.generateProperties(Data.class, polysynth);
  });
}}
