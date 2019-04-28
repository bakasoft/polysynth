package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.TestCase;
import org.bakasoft.polysynth.testData.Artist;
import org.bakasoft.polysynth.wrappers.ArrayWrapper;
import org.bakasoft.polysynth.wrappers.ObjectWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArrayClassSchema_wrapNew_wrap extends TestCase {{
  Polysynth polysynth = new Polysynth();
  ArrayClassSchema schema = new ArrayClassSchema(polysynth, Byte[].class);

  // wrap existing array
  {
    Byte[] array = { 0x00, 0x7F, 0x1C };
    ArrayWrapper wrapper = schema.wrap(array);

    itMustBeSame(wrapper.getInstance(), array);
    itMustBeSame(wrapper.getSchema(), schema);
  }

  // wrap new empty array
  {
    ArrayWrapper wrapper = schema.wrapNew();
    Object instance = wrapper.getInstance();

    itMustBeInstanceOf(instance, Byte[].class);
  }

  // wrap new initialized array
  {
    ArrayWrapper wrapper = schema.wrapNew(Arrays.asList((byte)0x00, (byte)0x01, (byte)0x02));
    Object instance = wrapper.getInstance();

    itMustBeInstanceOf(instance, Byte[].class);

    Byte[] array = (Byte[])instance;

    itMustIterate(array, (byte)0x00, (byte)0x01, (byte)0x02);
  }
}}
