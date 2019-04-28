package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.PropertyReadException;
import org.bakasoft.polysynth.errors.PropertyWriteException;

public class FieldProperty_set_get extends TestCase {{
  class Data {
    public String field1;
    public final String field2 = "";
    private String field3;
  }

  // happy path
  {
    FieldProperty prop1 = FieldPropertyHelper.create(Data.class, "field1");
    Data data = new Data();

    prop1.set(data, "a");

    itMustBeEqual(prop1.get(data), "a");
  }

  // read-only fields
  {
    FieldProperty prop2 = FieldPropertyHelper.create(Data.class, "field2");
    Data data = new Data();

    itMustFailWith(PropertyWriteException.class, () -> {
      prop2.set(data, "a");
    });
  }

  // not accessible fields
  {
    FieldProperty prop3 = FieldPropertyHelper.create(Data.class, "field3");
    Data data = new Data();

    itMustFailWith(PropertyWriteException.class, () -> {
      prop3.set(data, "a");
    });

    itMustFailWith(PropertyReadException.class, () -> {
      prop3.get(data);
    });
  }

}}
