package org.bakasoft.polysynth.graph;

import org.bakasoft.polysynth.Polysynth;

import java.util.HashMap;

public class GraphContext {

  private final Polysynth polysynth;

  private final HashMap<GraphRef, GraphObject> references;

  public GraphContext(Polysynth polysynth) {
    this.polysynth = polysynth;
    this.references = new HashMap<>();
  }

  public Polysynth getPolysynth() {
    return polysynth;
  }

  synchronized public GraphRef search(Object item) {
    if (item == null) {
      return null;
    }

    int id = item.hashCode();
    String type = item.getClass().getName();
    GraphRef ref = new GraphRef(id, type);
    GraphObject obj = references.get(ref);

    if (obj == null) {
      return null;
    }

    return ref;
  }

  synchronized public void register(int id, String type, GraphObject obj) {
    GraphRef ref = new GraphRef(id, type);

    references.put(ref, obj);
  }
}
