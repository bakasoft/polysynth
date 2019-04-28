package org.bakasoft.polysynth.graph;

public interface GraphValue {

  default String toJson() {
    StringBuilder builder = new StringBuilder();
    toJson(builder);
    return builder.toString();
  }

  void toJson(StringBuilder builder);

}
