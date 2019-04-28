package org.bakasoft.polysynth.schemas;

import org.bakasoft.polysynth.Polysynth;
import org.bakasoft.polysynth.errors.UnexpectedTypeException;
import org.bakasoft.polysynth.graph.GraphContext;
import org.bakasoft.polysynth.graph.GraphValue;

public interface Schema {

  GraphValue toGraph(Object instance, GraphContext context);

  Class<?> getType();

  default GraphValue toGraph(Object instance, Polysynth polysynth) {
    return toGraph(instance, new GraphContext(polysynth));
  }

  default ObjectSchema toObject() {
    if (!(this instanceof ObjectSchema)) {
      throw new UnexpectedTypeException(this, ObjectSchema.class);
    }

    return (ObjectSchema)this;
  }

  default ArraySchema toArray() {
    if (!(this instanceof ArraySchema)) {
      throw new UnexpectedTypeException(this, ArraySchema.class);
    }

    return (ArraySchema)this;
  }

  default ScalarSchema toScalar() {
    if (!(this instanceof ScalarSchema)) {
      throw new UnexpectedTypeException(this, ScalarSchema.class);
    }

    return (ScalarSchema)this;
  }

}
