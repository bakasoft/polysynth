package org.bakasoft.polysynth.wrappers;

import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.schemas.ArraySchema;

import java.util.List;

public class ArrayWrapper {

  private final Object instance;
  private final ArraySchema schema;

  public ArrayWrapper(Object instance, ArraySchema schema) {
    if (instance == null) { throw new MissingArgumentException("instance"); }
    if (schema == null) { throw new MissingArgumentException("schema"); }

    this.instance = instance;
    this.schema = schema;
  }

  public void addItem(Object item) {
    schema.add(instance, item);
  }

  // TODO add missing mutation methods

  public List<Object> getItems() {
    return schema.list(instance);
  }

  public Object getInstance() {
    return instance;
  }

  public ArraySchema getSchema() {
    return schema;
  }

}
