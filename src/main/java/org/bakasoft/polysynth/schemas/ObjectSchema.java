package org.bakasoft.polysynth.schemas;

import org.bakasoft.beat.BeatProperty;
import org.bakasoft.beat.BeatType;
import org.bakasoft.beat.BeatConstructor;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.*;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphObject;
import org.bakasoft.polysynth.graph.GraphValue;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ObjectSchema extends Schema {

  private final Polysynth polysynth;

  private final BeatType beatType;

  private final Map<String, Schema> schemas;
  private final BeatConstructor beatConstructor;

  public ObjectSchema(Type type) {
    this(new Polysynth(), type);
  }

  public ObjectSchema(Polysynth polysynth, Type type) {
    this(polysynth, new BeatType(type));
  }

  public ObjectSchema(Polysynth polysynth, BeatType beatType) {
    if (polysynth == null) { throw new MissingArgumentException("polysynth"); }
    if (beatType == null) { throw new MissingArgumentException("beatType"); }

    this.polysynth = polysynth;
    this.beatType = beatType;
    this.beatConstructor = beatType.getConstructor();
    this.schemas = new HashMap<>();
  }

  @Override
  public Object convert(Object value, ConversionCache cache) {
    if (beatType.isCompatible(value)) {
      return value;
    }
    else if (value instanceof Map) {
      return createWith((Map<?,?>)value, cache);
    }
    else {
      throw new ConversionException(value, beatType.getTypeClass());
    }
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    return new GraphObject(context, this, instance);
  }

  @Override
  public Class<?> getType() {
    return beatType.getTypeClass();
  }

  @Override
  public GraphValue toGraph(Object instance) {
    return toGraph(instance, new GraphContext(polysynth));
  }

  public Set<String> getKeys() {
    return beatType.getPropertyNames();
  }

  public Schema getSchema(String key) {
    synchronized (schemas) {
      if (schemas.containsKey(key)) {
        return schemas.get(key);
      }

      BeatProperty property = beatType.getProperty(key);
      Schema schema;

      if (property == null) {
        schema = null;
      }
      else {
        schema = polysynth.getSchema(property.getRawType());
      }

      schemas.put(key, schema);

      return schema;
    }
  }

  public boolean isReadable(String key) {
    BeatProperty p = beatType.getProperty(key);

    if (p == null) {
      throw new UnknownPropertyException(beatType.getTypeClass(), key);
    }

    return p.isReadable();
  }

  public boolean isWritable(String key) {
    BeatProperty p = beatType.getProperty(key);

    if (p == null) {
      throw new UnknownPropertyException(beatType.getTypeClass(), key);
    }

    return p.isWritable();
  }

  public Object createWith(Map<?, ?> rawValues) {
    return createWith(rawValues, null);
  }

  private Object createWith(Map<?,?> rawValues, ConversionCache cache) {
    if (rawValues == null) {
      return createEmpty();
    }

    HashMap<String, Object> validValues = toValidNamedArguments(beatType, rawValues, cache);

    return beatConstructor.createWithNamedArguments(validValues);
  }

  private HashMap<String, Object> toValidNamedArguments(BeatType type, Map<?,?> raw, ConversionCache cache) {
    class PropertyTemplate {
      public String key;
      public Schema schema;
      public Object value;
    }

    ArrayList<PropertyTemplate> templates = new ArrayList<>();

    for (Map.Entry<?, ?> entry : raw.entrySet()) {
      if (entry.getKey() instanceof String) {
        String key = (String)entry.getKey();
        Schema schema = getSchema(key);

        if (schema == null) {
          throw new UnknownPropertyException(type.getTypeClass(), key);
        }

        PropertyTemplate template = new PropertyTemplate();
        template.key = key;
        template.schema = schema;
        template.value = entry.getValue();
        templates.add(template);
      }
      else {
        throw new UnexpectedTypeException(entry.getKey(), String.class);
      }
    }

    HashMap<String, Object> result = new HashMap<>();

    ConversionCache actualCache;

    if (cache == null) {
      actualCache = new ConversionCache();
    }
    else {
      actualCache = cache;
    }

    for (PropertyTemplate template : templates) {
      String key = template.key;
      Schema schema = template.schema;
      Object rawValue = template.value;
      Object validValue = actualCache.getOrConvert(schema, rawValue);

      result.put(key, validValue);
    }

    return result;
  }

  public void set(Object instance, String key, Object value) {
    beatType.set(instance, key, value);
  }

  public Object get(Object instance, String key) {
    return beatType.get(instance, key);
  }

  @Override
  public String toString() {
    return "Object<" + beatType.getName() + ">";
  }

  public Class<?> getType(String key) {
    Schema schema = getSchema(key);

    if (schema == null) {
      return null;
    }

    return schema.getType();
  }

  public Object createEmpty() {
    return beatConstructor.createDefault();
  }

  public ObjectSchema toObject() {
    return this;
  }

  public ArraySchema toArray() {
    throw new UnexpectedTypeException(this, ArraySchema.class);
  }

}
