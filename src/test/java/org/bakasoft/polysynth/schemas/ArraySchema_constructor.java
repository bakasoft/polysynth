package org.bakasoft.polysynth.schemas;

import org.bakasoft.framboyan.test.TestCase;
import org.bakasoft.beat.BeatType;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.MissingArgumentException;

import java.lang.reflect.Type;

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
