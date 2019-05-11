package org.bakasoft.polysynth.schemas;

import org.bakasoft.beat.BeatConstructor;
import org.bakasoft.beat.BeatType;
import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.ConversionException;
import org.bakasoft.polysynth.errors.MissingArgumentException;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.graph.GraphArray;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphValue;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ArraySchema extends Schema {

  private final Polysynth polysynth;

  private final BeatType beatType;
  private final BeatConstructor beatConstructor;

  private final Schema componentSchema;

  public ArraySchema(Type type) {
    this(new Polysynth(), type);
  }

  public ArraySchema(Polysynth polysynth, Type type) {
    this(polysynth, new BeatType(type));
  }

  public ArraySchema(Polysynth polysynth, BeatType beatType) {
    if (polysynth == null) { throw new MissingArgumentException("polysynth"); }
    if (beatType == null) { throw new MissingArgumentException("beatType"); }

    this.polysynth = polysynth;
    this.beatType = beatType;
    this.beatConstructor = beatType.getConstructor();
    this.componentSchema = beatType.hasComponent() ? polysynth.getSchema(beatType.getComponentType().getRawType()) : null;
  }

  public void add(Object instance, Object value) {
    beatType.add(instance, value);
  }

  // TODO implement: remove(instance, index) insert(instance, index, item) set(instance, index, item)

  public List<?> list(Object instance) {
    return beatType.list(instance);
  }

  public Schema getComponentSchema() {
    return componentSchema;
  }

  public Object createWith(List<?> items) {
    return createWith(items, null);
  }

  public Object createWith(List<?> items, ConversionCache cache) {
    if (items == null) {
      return createEmpty();
    }

    List<?> validItems = toValidItems(items, cache);

    return beatType.getConstructor().createWithInitialContent(validItems);
  }

  private List<?> toValidItems(List<?> items, ConversionCache cache) {
    if (componentSchema == null) {
      return items;
    }

    ConversionCache actualCache;

    if (cache == null) {
      actualCache = new ConversionCache();
    }
    else {
      actualCache = cache;
    }

    return items.stream()
        .map(item -> actualCache.getOrConvert(componentSchema, item))
        .collect(Collectors.toList());
  }

  public Object createEmpty() {
    return beatConstructor.createDefault();
  }

  @Override
  public Object convert(Object value, ConversionCache cache) {
    if (beatType.isCompatible(value)) {
      return value;
    }
    else if (value instanceof List) {
      return createWith((List<?>)value, cache);
    }
    else {
      throw new ConversionException(value, beatType.getTypeClass());
    }
  }

  @Override
  public GraphValue toGraph(Object instance, GraphContext context) {
    return new GraphArray(context, this, instance);
  }

  @Override
  public Class<?> getType() {
    return beatType.getTypeClass();
  }

  @Override
  public GraphValue toGraph(Object instance) {
    return toGraph(instance, new GraphContext(polysynth));
  }

  public ObjectSchema toObject() {
    throw new UnexpectedTypeException(this, ObjectSchema.class);
  }

  public ArraySchema toArray() {
    return this;
  }

  @Override
  public String toString() {
    Class<?> cc = beatType.getComponentClass();

    return "Array<" + (cc != null ? cc.getSimpleName() : "?") + ">";
  }
}
