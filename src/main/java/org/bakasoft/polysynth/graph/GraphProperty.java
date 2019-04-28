package org.bakasoft.polysynth.graph;

public class GraphProperty {

  private final String name;
  private final GraphValue value;

  public GraphProperty(String name, GraphValue value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public GraphValue getValue() {
    return value;
  }
}
