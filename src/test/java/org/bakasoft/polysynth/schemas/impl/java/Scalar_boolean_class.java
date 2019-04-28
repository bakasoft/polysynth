package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphBoolean;

public class Scalar_boolean_class extends TestCase {{
  Scalar_boolean schema = new Scalar_boolean();

  itMustBeEqual(schema.getType(), boolean.class);
  itMustBeEqual(schema.toString(), "Scalar<boolean>");
  itMustBeInstanceOf(schema.toGraph(true, new Polysynth()), GraphBoolean.class);

  itMustBeEqual(schema.convert(true), true);
  itMustBeEqual(schema.convert(false), false);
  itMustBeEqual(schema.convert("true"), true);
  itMustBeEqual(schema.convert("false"), false);
  itMustBeEqual(schema.convert(Boolean.TRUE), true);
  itMustBeEqual(schema.convert(Boolean.FALSE), false);
  itMustFailWith(ConversionException.class, () -> schema.convert(null));
  itMustFailWith(ConversionException.class, () -> schema.convert(0));
  itMustFailWith(ConversionException.class, () -> schema.convert(1));
  itMustFailWith(ConversionException.class, () -> schema.convert("yes"));
  itMustFailWith(ConversionException.class, () -> schema.convert("no"));
  itMustFailWith(ConversionException.class, () -> schema.convert(""));
  itMustFailWith(ConversionException.class, () -> schema.convert(new Object()));
}}
