package org.bakasoft.polysynth.schemas;

public interface ScalarSchema extends Schema {

  Object convert(Object value);

}
