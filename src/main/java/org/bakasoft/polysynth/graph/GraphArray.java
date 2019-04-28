package org.bakasoft.polysynth.graph;

import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.Schema;

import java.util.List;

public class GraphArray implements GraphValue {

  private final GraphContext context;
  private final ArraySchema schema;
  private final Object instance;

  private boolean ready;

  private GraphValue[] values_;

  public GraphArray(GraphContext context, ArraySchema schema, Object instance) {
    this.context = context;
    this.schema = schema;
    this.instance = instance;
  }

  public GraphValue[] getValues() {
    if (!ready) { load(); }
    return values_;
  }

  synchronized private void load() {
    List<Object> sourceItems = schema.list(instance);
    Schema globalSchema = schema.getItemSchema();

    values_ = new GraphValue[sourceItems.size()];

    for (int i = 0; i < values_.length; i++) {
      Object sourceItem = sourceItems.get(i);
      GraphValue graphItem;

      if (sourceItem == null) {
        graphItem = null;
      }
      else {
        GraphRef ref = context.search(sourceItem);

        if (ref != null) {
          graphItem = ref;
        }
        else {
          Schema itemSchema;

          if (globalSchema == null) {
            itemSchema = context.getPolysynth().getSchema(sourceItem.getClass());
          } else {
            itemSchema = globalSchema;
          }

          graphItem = itemSchema.toGraph(sourceItem, context);
        }
      }

      values_[i] = graphItem;
    }

    ready = true;
  }

  @Override
  public void toJson(StringBuilder builder) {
    GraphValue[] values = getValues();

    // TODO improve json format
    builder.append('[');
    for (int i = 0; i < values.length; i++) {
      if (i > 0) {
        builder.append(',');
      }
      if (values[i] != null) {
        values[i].toJson(builder);
      }
      else {
        builder.append("null");
      }
    }
    builder.append(']');
  }
}
