package org.bakasoft.polysynth.graph;

public class GraphBoolean implements GraphValue {

  private final Boolean value;

  public GraphBoolean(Boolean value) {
    this.value = value;
  }

  public Boolean getValue() {
    return value;
  }

  @Override
  public void toJson(StringBuilder builder) {
    // TODO improve json format
    builder.append(value.toString());
  }
}
