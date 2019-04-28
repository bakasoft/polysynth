package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.AmbiguousPropertyException;

import java.util.Map;

public class ClassProperty_generateProperties_5 extends TestCase {{
  class Data {
    public void set(Object value) {}
    public boolean get() { return false; }
    public boolean is() { return false; }
    public void setURL(String value) {}
  }

  Polysynth polysynth = new Polysynth();
  Map<String, ClassProperty> props = ClassProperty.generateProperties(Data.class, polysynth);

  itMustBeEqual(props.size(), 1);
  itMustBeEqual(props.containsKey("URL"), true);
}}
