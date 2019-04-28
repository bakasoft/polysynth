package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.wrappers.ArrayWrapper;

import java.util.List;

public interface ArraySchema extends Schema {

  void add(Object instance, Object value);

  // TODO implement: remove(instance, index) insert(instance, index, item) set(instance, index, item)

  List<Object> list(Object instance);

  Schema getItemSchema();

  Object createWith(List<?> items);

  default Object createEmpty() {
    return createWith(null);
  }

  default ArrayWrapper wrapNew() {
    return wrap(createEmpty());
  }

  default ArrayWrapper wrapNew(List<?> items) {
    return wrap(createWith(items));
  }

  default ArrayWrapper wrap(Object instance) {
    return new ArrayWrapper(instance, this);
  }

}
