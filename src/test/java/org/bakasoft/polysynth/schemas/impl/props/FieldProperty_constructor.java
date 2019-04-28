package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.TestError;

public class FieldProperty_constructor extends TestCase {{
  class Data {
    public String field1;
  }

  itMustPass(() -> {
    new FieldProperty(
        new Polysynth(),
        Data.class,
        "field1",
        Data.class.getField("field1"));
  });
}}
