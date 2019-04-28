package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.PropertyReadException;
import org.bakasoft.polysynth.errors.PropertyWriteException;

import java.util.Map;

public class MethodProperty_get_set_2 extends TestCase {{
  class Data {
    private String getValue() { return null; }
    private void setValue(String value) {}
  }

  MethodProperty property = MethodPropertyHelper.create(Data.class, "value", "getValue", "setValue");

  itMustFailWith(PropertyWriteException.class, () -> {
    property.set(new Data(), "");
  });

  itMustFailWith(PropertyReadException.class, () -> {
    property.get(new Data());
  });
}}
