package org.bakasoft.polysynth.graph;

public class GraphString implements GraphValue {

  private final String value;

  public GraphString(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public void toJson(StringBuilder builder) {
    // TODO improve json format
    builder.append("\"" + value + "\"");
  }
}
