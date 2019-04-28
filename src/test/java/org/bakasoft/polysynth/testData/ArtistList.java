package org.bakasoft.polysynth.testData;

import java.util.ArrayList;
import java.util.Arrays;

public class ArtistList extends ArrayList<Artist> {

  public ArtistList(Artist... items) {
    super(Arrays.asList(items));
  }

}
