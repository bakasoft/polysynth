package org.bakasoft.polysynth.errors;

import java.util.Objects;

abstract public class PolysynthException extends RuntimeException {

  public PolysynthException(String message) {
    super(message);
  }

  public PolysynthException(String message, Throwable cause) {
    super(message, cause);
  }

  public static String inspect(Object obj) {
    if (obj instanceof Class) {
      return ((Class<?>)obj).getName();
    }
    else if (obj == null) {
      return "null";
    }

    Class<?> type = obj.getClass();
    String defstr = type.getSimpleName() + "@" + Integer.toHexString(obj.hashCode());
    String actstr = obj.toString();

    if (Objects.equals(defstr, actstr)) {
      return defstr;
    }

    // TODO escape and look for multi-line and long text
    return defstr + "<" + actstr + ">";
  }

}
