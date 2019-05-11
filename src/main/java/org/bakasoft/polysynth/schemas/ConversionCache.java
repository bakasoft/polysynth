package org.bakasoft.polysynth.schemas;

import java.util.HashMap;

public class ConversionCache {

  private HashMap<Schema, HashMap<Object, Object>> schemaCache;

  public Object getOrConvert(Schema schema, Object value) {
    if (schemaCache == null) {
      schemaCache = new HashMap<>();
    }

    HashMap<Object, Object> objectCache = schemaCache.computeIfAbsent(schema, s -> new HashMap<>());

    if (objectCache.containsKey(value)) {
      return objectCache.get(value);
    }

    Object result = schema.convert(value, this);

    objectCache.put(value, result);

    return result;
  }

}
