package org.bakasoft.polysynth.util;

import org.bakasoft.polysynth.errors.MissingConstructorException;
import org.bakasoft.polysynth.errors.ObjectCreationException;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ReflectionHelper {
  public static Supplier<Object> makeObjectCreator(Class<?> type) {
    for (Constructor<?> ctr : type.getConstructors()) {
      if (ctr.getParameterCount() == 0) {
        return () -> {
          try {
            return ctr.newInstance();
          }
          catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ObjectCreationException(type, e);
          }
        };
      }
    }

    throw new MissingConstructorException(type);
  }

  public static Supplier<? extends List<?>> makeListConstructor(Class<?> type) {
    for (Constructor<?> ctr : type.getConstructors()) {
      if (ctr.getParameterCount() == 0) {
        return () -> {
          try {
            return (List<?>)ctr.newInstance();
          }
          catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ObjectCreationException(type, e);
          }
        };
      }
    }

    // fallback class
    if (type.isAssignableFrom(ArrayList.class)) {
      return ArrayList::new;
    }

    throw new ObjectCreationException(type);
  }

  public static List<Class<?>> collectClasses(Predicate<Class<?>> condition) {
    ArrayList<Class<?>> result = new ArrayList<>();

    try {
      Enumeration<URL> resources = ClassLoader.getSystemResources("");

      while (resources.hasMoreElements()) {
        URL url = resources.nextElement();
        Path root = Paths.get(url.toURI());

        Files.walk(root)
            .filter(Files::isRegularFile)
            .filter(path -> path.toString().toLowerCase().endsWith(".class"))
            .map(root::relativize)
            .map(Path::toString)
            .map(path -> path
                .substring(0, path.length() - 6)
                .replace('/', '.'))
            .map(typeName -> {
              try {
                return Class.forName(typeName);
              }
              catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
              }
            })
            .filter(condition)
            .forEach(result::add);
      }
    }
    catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }

    return result;
  }
}
