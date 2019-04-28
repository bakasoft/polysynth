package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.AmbiguousPropertyException;

import java.util.Map;

public class MethodProperty_constructor_1 extends TestCase {{
  class Data {
    public void setValue(String value) {}
    public int getValue() { return 0; }
  }

  Polysynth polysynth = new Polysynth();

  itMustFailWith(AmbiguousPropertyException.class, () -> {
    ClassProperty.generateProperties(Data.class, polysynth);
  });

}}
