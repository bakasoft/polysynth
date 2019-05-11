package org.bakasoft.polysynth;

import org.bakasoft.framboyan.test.Framboyan;
import org.bakasoft.polysynth.util.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RunTests {

  public static void main(String[] args) {
    Framboyan.run("org.bakasoft.polysynth");
  }

}
