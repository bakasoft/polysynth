package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.wrappers.ObjectWrapper;

import java.util.Map;
import java.util.Set;

public interface ObjectSchema extends Schema {

  void set(Object instance, String key, Object value);

  Object get(Object instance, String key);

  Set<String> getKeys();

  Schema getSchema(String key);

  boolean isReadOnly(String key);

  boolean isWriteOnly(String key);

  Object createWith(Map<String, ?> initialValues);

  default Class<?> getType(String key) {
    Schema schema = getSchema(key);

    if (schema == null) {
      return null;
    }

    return schema.getType();
  }

  default Object createEmpty() {
    return createWith(null);
  }

  default ObjectWrapper wrapNew() {
    return wrap(createEmpty());
  }

  default ObjectWrapper wrapNew(Map<String, ?> initialValues) {
    return wrap(createWith(initialValues));
  }

  default ObjectWrapper wrap(Object instance) {
    return new ObjectWrapper(instance, this);
  }

}
