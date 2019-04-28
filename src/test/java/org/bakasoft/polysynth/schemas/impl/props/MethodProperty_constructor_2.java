package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.AmbiguousPropertyException;

public class MethodProperty_constructor_2 extends TestCase {{
  itMustFailWith(AmbiguousPropertyException.class, () -> {
    new MethodProperty(
        new Polysynth(),
        Object.class,
        "property",
        null, null, null, null);
  });

}}
