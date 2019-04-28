package org.bakasoft.polysynth.graph;

import java.util.Objects;

public class GraphRef implements GraphValue {

  private final int id;
  private final String type;

  public GraphRef(int id, String type) {
    this.id = id;
    this.type = type;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof GraphRef) {
      GraphRef ref = (GraphRef)obj;

      return id == ref.id && type.equals(ref.type);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public void toJson(StringBuilder builder) {
    // TODO escape strings
    builder.append("{\"@ref\":\"");
    builder.append(Integer.toHexString(id));
    builder.append("\",\"@type\":\"");
    builder.append(type);
    builder.append("\"}");
  }
}
