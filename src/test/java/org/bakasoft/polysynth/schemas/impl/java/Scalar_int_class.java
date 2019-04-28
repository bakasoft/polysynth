package org.bakasoft.polysynth.schemas.impl.java;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.graph.GraphBoolean;
import org.bakasoft.polysynth.graph.GraphNumber;

public class Scalar_int_class extends TestCase {{
  Scalar_int schema = new Scalar_int();

  itMustBeEqual(schema.getType(), int.class);
  itMustBeEqual(schema.toString(), "Scalar<int>");
  itMustBeInstanceOf(schema.toGraph(10, new Polysynth()), GraphNumber.class);

  itMustBeEqual(schema.convert(10), 10);
  itMustBeEqual(schema.convert(10L), 10);
  itMustBeEqual(schema.convert("10"), 10);
  itMustFailWith(ConversionException.class, () -> schema.convert(null));
  itMustFailWith(ConversionException.class, () -> schema.convert(true));
  itMustFailWith(ConversionException.class, () -> schema.convert(false));
  itMustFailWith(ConversionException.class, () -> schema.convert("10A"));
  itMustFailWith(ConversionException.class, () -> schema.convert("1,1"));
  itMustFailWith(ConversionException.class, () -> schema.convert(""));
  itMustFailWith(ConversionException.class, () -> schema.convert(new Object()));
}}
