package org.bakasoft.polysynth.graph;

import org.bakasoft.polysynth.errors.MissingArgumentException;

public class GraphNumber implements GraphValue {

  private final Number value;

  public GraphNumber(Number value) {
    if (value == null) {
      throw new MissingArgumentException("value");
    }
    this.value = value;
  }

  public Number getValue() {
    return value;
  }

  @Override
  public void toJson(StringBuilder builder) {
    // TODO improve json format
    builder.append(value);
  }
}
