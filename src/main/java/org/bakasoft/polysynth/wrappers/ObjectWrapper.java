package org.bakasoft.polysynth.wrappers;

import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.schemas.ObjectSchema;

import java.util.Set;

public class ObjectWrapper {

  private final Object instance;
  private final ObjectSchema schema;

  public ObjectWrapper(Object instance, ObjectSchema schema) {
    if (instance == null) { throw new MissingArgumentException("instance"); }
    if (schema == null) { throw new MissingArgumentException("schema"); }

    this.instance = instance;
    this.schema = schema;
  }

  public Object getValue(String key) {
    return schema.get(instance, key);
  }

  public void setValue(String key, Object value) {
    schema.set(instance, key, value);
  }

  public Set<String> getKeys() {
    return schema.getKeys();
  }

  public Object getInstance() {
    return instance;
  }

  public ObjectSchema getSchema() {
    return schema;
  }

}
