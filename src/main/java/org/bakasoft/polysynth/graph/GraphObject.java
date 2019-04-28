package org.bakasoft.polysynth.graph;

import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;

import java.util.Set;

public class GraphObject implements GraphValue {

  private final GraphContext context;
  private final ObjectSchema schema;
  private final Object instance;
  private final int id;
  private final String type;

  private boolean ready;

  private GraphProperty[] properties_;

  public GraphObject(GraphContext context, ObjectSchema schema, Object instance) {
    this.context = context;
    this.schema = schema;
    id = instance.hashCode();
    type = schema.getType().getName();

    context.register(id, type, this);

    this.instance = instance;
  }

  synchronized private void load() {
    Set<String> keys = schema.getKeys();

    properties_ = new GraphProperty[keys.size()];
    int i = 0;

    for (String key : keys) {
      Schema keySchema = schema.getSchema(key);
      Object keyValue = schema.get(instance, key);
      GraphValue graphValue;

      if (keyValue == null) {
        graphValue = null;
      }
      else {
        GraphRef ref = context.search(keyValue);

        if (ref != null) {
          graphValue = ref;
        } else {
          graphValue = keySchema.toGraph(keyValue, context);
        }
      }

      properties_[i] = new GraphProperty(key, graphValue);
      i++;
    }

    ready = true;
  }

  public int getId() {
    if (!ready) { load(); }
    return id;
  }

  public String getType() {
    if (!ready) { load(); }
    return type;
  }

  public GraphProperty[] getProperties() {
    if (!ready) { load(); }
    return properties_;
  }

  @Override
  public void toJson(StringBuilder builder) {
    builder.append("{\"@id\":\"");
    builder.append(Integer.toHexString(id));
    builder.append("\",\"@type\":\"");
    builder.append(type);
    builder.append('"');

    GraphProperty[] properties = getProperties();

    for (int i = 0; i < properties.length; i++) {
      GraphProperty property = properties[i];
      String name = property.getName();
      GraphValue value = property.getValue();

      builder.append(',');
      builder.append("\"");
      builder.append(name); // TODO escape string
      builder.append("\"");
      builder.append(":");
      if (value != null) {
        value.toJson(builder);
      }
      else {
        builder.append("null");
      }
    }
    builder.append('}');
  }
}
