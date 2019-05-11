package org.bakasoft.polysynth.schemas;

import org.bakasoft.beat.BeatType;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.schemas.ArraySchema;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class ArraySchema_constructor extends TestCase {{

  fail("Missing type", NullPointerException.class, () -> {
    new ArraySchema(null);
  });

  fail("Missing type with polysynth", NullPointerException.class, () -> {
    new ArraySchema(new Polysynth(), (Type) null);
  });

  fail("Missing beat-type with polysynth", MissingArgumentException.class, () -> {
    new ArraySchema(new Polysynth(), (BeatType) null);
  });

  fail("Missing polysynth with type", MissingArgumentException.class, () -> {
    new ArraySchema(null, Object.class);
  });

  fail("Missing Polysynth with beat-type", MissingArgumentException.class, () -> {
    new ArraySchema(null, new BeatType(Object.class));
  });
}}
