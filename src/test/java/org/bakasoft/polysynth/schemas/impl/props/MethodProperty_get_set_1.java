package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.PropertyReadException;
import org.bakasoft.polysynth.errors.PropertyWriteException;

import java.util.Map;

public class MethodProperty_get_set_1 extends TestCase {{
  class Data {
    public String getStatus() { return ""; } // read-only
    public void setActive(boolean active) { throw new RuntimeException(); }
    public boolean getActive() { throw new RuntimeException(); }
    public void setAmount(double amount) {} // write-only
  }

  Polysynth polysynth = new Polysynth();
  Map<String, ClassProperty> props = ClassProperty.generateProperties(Data.class, polysynth);

  itMustFailWith(PropertyWriteException.class, () -> {
    props.get("status").set(new Data(), "");
  });
  itMustFailWith(PropertyWriteException.class, () -> {
    props.get("active").set(new Data(), true);
  });
  itMustFailWith(PropertyReadException.class, () -> {
    props.get("amount").get(new Data());
  });
  itMustFailWith(PropertyReadException.class, () -> {
    props.get("active").get(new Data());
  });
}}
