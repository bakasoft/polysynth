package org.bakasoft.polysynth;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.bakasoft.polysynth.errors.PolysynthException.inspect;

public class TestCase {

  public interface RunnableException {
    void run() throws Exception;
  }

  public void itMustPass(RunnableException action) {
    try {
      action.run();
    }
    catch (Exception e) {
      throw new TestError(this, "Unexpected exception.", e);
    }
  }

  public void itMustFailWith(Class<? extends Throwable> errorClass, RunnableException action) {
    try {
      action.run();

      throw new TestError(this, "Expected an exception.");
    }
    catch (Exception e) {
      itMustBeInstanceOf(e, errorClass);
    }
  }

  public void itMustBeInstanceOf(Object instance, Class<?> type) {
    if (instance == null) {
      throw new TestError(this, "Expected a non-null instance.");
    }
    if (type == null) {
      throw new TestError(this, "Expected a non-null type.");
    }

    if (!type.isInstance(instance)) {
      throw new TestError(this, String.format(
          "Expected the object %s to be an instance of %s.",
          inspect(instance),
          inspect(type)));
    }
  }

  public void itMustBeEqual(Object actual, Object expected) {
    if (!Objects.equals(actual, expected)) {
      throw new TestError(this, String.format(
          "Expected the value %s be equal to %s.",
          inspect(actual),
          inspect(expected)));
    }
  }

  public void itMustBeSame(Object actual, Object expected) {
    if (actual != expected) {
      throw new TestError(this, String.format(
          "Expected the value %s be the same than %s.",
          inspect(actual),
          inspect(expected)));
    }
  }

  public void itMustIterate(Object source, Object... items) {
    List<Object> list = tryListify(source);

    if (list == null) {
      throw new TestError(this, String.format(
          "Unsupported source: %s.",
          inspect(source)));
    }

    int actualLength = list.size();
    int expectedLength = items.length;
    int minLength = Math.min(actualLength, expectedLength);

    // check first the order or the matching items
    for (int i = 0; i < minLength; i++) {
      Object actualItem = list.get(i);
      Object expectedItem = items[i];

      if (!Objects.equals(actualItem, expectedItem)) {
        throw new TestError(this, String.format(
            "Expected item %s at index %s to be equal to %s.",
            inspect(actualItem),
            inspect(i),
            inspect(expectedItem)));
      }
    }

    if (actualLength != expectedLength) {
      throw new TestError(this, String.format(
          "Expected length of list to be %s instead of %s.",
          inspect(expectedLength),
          inspect(actualLength)));
    }
  }

  public void itMustContain(Object source, Object... items) {
    if (items == null || items.length == 0) {
      throw new TestError(this,
          "Expected one or more item.");
    }

    if (source instanceof String) { // text mode
      String text = (String)source;

      for (Object item : items) {
        String itemText = String.valueOf(item);

        if (!text.contains(itemText)) {
          throw new TestError(this, String.format(
              "Expected the array %s contains item %s.",
              inspect(text),
              inspect(itemText)));
        }
      }
    }
    else { // list mode
      List<Object> list = tryListify(source);

      if (list == null) {
        throw new TestError(this, String.format(
            "Unsupported source: %s.",
            inspect(source)));
      }

      for (Object item : items) {
        if (!list.contains(item)) {
          throw new TestError(this, String.format(
              "Expected the array %s contains item %s.",
              inspect(source),
              inspect(item)));
        }
      }
    }
  }

  private static List<Object> tryListify(Object source) {
    if (source instanceof Iterable) {
      ArrayList<Object> result = new ArrayList<>();
      Iterable<?> iterable = (Iterable<?>)source;

      for (Object item : iterable) {
        result.add(item);
      }

      return result;
    }
    else if (source != null && source.getClass().isArray()) {
      int length = Array.getLength(source);
      ArrayList<Object> result = new ArrayList<>(length);

      for (int i = 0; i < length; i++) {
        result.add(Array.get(source, i));
      }

      return result;
    }

    return null;
  }
}