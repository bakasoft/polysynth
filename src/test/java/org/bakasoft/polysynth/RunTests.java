package org.bakasoft.polysynth;

import org.bakasoft.polysynth.util.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RunTests {

  public static void main(String[] args) {
    // select only direct subclasses of TestCase
    List<Class<?>> testTypes = ReflectionHelper.collectClasses(type -> type.getSuperclass() == TestCase.class);
    List<Class<?>> passedTests = Collections.synchronizedList(new ArrayList<>());
    Map<Class<?>, Exception> failedTests = new ConcurrentHashMap<>();

    // run tests in parallel
    testTypes.stream().parallel().forEach(testType -> {
      try {
        testType.getConstructor().newInstance();

        passedTests.add(testType);
      }
      catch (Exception e) {
        failedTests.put(testType, e);
      }
    });

    int successCount = passedTests.size();
    int errorCount = failedTests.size();

    passedTests.sort(Comparator.comparing(Class::getSimpleName));

    for (Class<?> testType : passedTests) {
      String description = testType.getSimpleName().replace('_', ' ');

      System.out.println("[OK] " + description);
    }

    failedTests.forEach((testType, e) -> {
      String description = testType.getSimpleName().replace('_', ' ');
      Throwable error;

      if (e instanceof InvocationTargetException) {
        error = ((InvocationTargetException)e).getTargetException();
      }
      else {
        error = e;
      }

      System.out.println();
      System.out.println("[ERROR] " + description + ":");

      while (error != null) {
        // TODO handle indentation properly (taking into account multi-line strings)
        if (error.getMessage() != null) {
          System.out.println("  " + error.getMessage());
        }

        System.out.println("  " + error.getClass().getName() + ":");

        for (StackTraceElement element : error.getStackTrace()) {
          System.out.println("    " + element);
        }

        error = error.getCause();
      }
    });

    // print final results
    System.out.println();
    if (successCount > 0 && errorCount == 0) {
      System.out.println("All tests passed! ✅");
    }
    else if (errorCount > 0) {
      System.out.println("Some tests failed ❌");
    }
    else {
      System.out.println("No tests found ⚠️");
    }
    System.out.println("  " + successCount + " successful test(s)");
    System.out.println("  " + errorCount + " failed test(s)");
  }

}
