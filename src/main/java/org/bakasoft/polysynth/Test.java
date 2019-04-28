package org.bakasoft.polysynth;

import org.bakasoft.polysynth.graph.GraphValue;
import org.bakasoft.polysynth.schemas.ArraySchema;
import org.bakasoft.polysynth.schemas.ObjectSchema;
import org.bakasoft.polysynth.schemas.Schema;
import org.bakasoft.polysynth.wrappers.ObjectWrapper;

import java.util.HashSet;
import java.util.Stack;

public class Test {

  public static enum Kind {
    KIND1,
    KIND2,
  }

  public static class User extends Exception {
    private String name;
    public String email;
    public Kind kind;
    public User loop;

    public String getName() {
      return name;
    }

    public void setName(String value) {
      this.name = value;
    }
  }

  public static void main(String args[]) throws Exception {
    Polysynth polysynth = new Polysynth();

    Stack<Schema> stack = new Stack<>();
    HashSet<Schema> printed = new HashSet<>();

    ObjectSchema userSchema = polysynth.getSchema(User.class).toObject();

    stack.push(userSchema);
    printed.add(userSchema);

    while (stack.size() > 0) {
      Schema schema = stack.pop();

      System.out.println(schema);

      if (schema instanceof ObjectSchema) {
        ObjectSchema os = (ObjectSchema)schema;

        for (String key : os.getKeys()) {
          Schema keySchema = os.getSchema(key);

          System.out.println("\t" + key + ": " + keySchema);

          if (!printed.contains(keySchema)) {
            printed.add(keySchema);
            stack.push(keySchema);
          }
        }
      }
      else if (schema instanceof ArraySchema) {
        Schema itemSchema = ((ArraySchema)schema).getItemSchema();

        if (!printed.contains(itemSchema)) {
          printed.add(itemSchema);
          stack.push(itemSchema);
        }
      }
    }

    ObjectWrapper wrap = userSchema.wrapNew();
    wrap.setValue("email", "sergio_uriel@hotmail.com");
    wrap.setValue("kind", Kind.KIND2);
    wrap.setValue("name", "Mat");
    wrap.setValue("loop", wrap.getInstance());
    GraphValue graph = userSchema.toGraph(wrap.getInstance(), polysynth);

    System.out.println(graph.toJson());
  }

}
