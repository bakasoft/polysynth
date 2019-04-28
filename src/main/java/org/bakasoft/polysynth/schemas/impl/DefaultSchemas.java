package org.bakasoft.polysynth.schemas.impl;

import org.bakasoft.polysynth.schemas.Schema;
import org.bakasoft.polysynth.schemas.impl.java.*;

import java.util.Map;

public class DefaultSchemas {

  public static void fill(Map<Class<?>, Schema> map) {
    map.put(String.class, new ScalarString());
    map.put(Boolean.class, new ScalarBoolean());
    map.put(Number.class, new ScalarNumber());
    map.put(Integer.class, new ScalarInteger());
    map.put(Double.class, new ScalarDouble());
    // TODO add all java schemas
  }

}
