package org.bakasoft.polysynth.schemas.impl.props;

import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.schemas.ScalarSchema;
import org.bakasoft.polysynth.schemas.Schema;

public class FieldProperty_getSchema extends TestCase {{
  class Data {
    public String field;
  }

  FieldProperty prop = FieldPropertyHelper.create(Data.class, "field");
  Schema propSchema = prop.getSchema();

  itMustBeInstanceOf(propSchema, ScalarSchema.class);
  itMustBeEqual(propSchema.getType(), String.class);
}}
