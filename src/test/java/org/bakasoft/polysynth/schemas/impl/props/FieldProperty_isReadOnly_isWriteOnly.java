package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;

public class FieldProperty_isReadOnly_isWriteOnly extends TestCase {{
  class Data {
    public String field1;
    public final String field2 = "";
  }

  FieldProperty prop1 = FieldPropertyHelper.create(Data.class, "field1");
  FieldProperty prop2 = FieldPropertyHelper.create(Data.class, "field2");

  itMustBeEqual(prop1.isReadOnly(), false);
  itMustBeEqual(prop2.isReadOnly(), true);

  // field properties can't be write-only
  itMustBeEqual(prop1.isWriteOnly(), false);
  itMustBeEqual(prop2.isWriteOnly(), false);
}}
