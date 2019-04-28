package org.bakasoft.polysynth.testData;

import java.util.ArrayList;
import java.util.UUID;

public class TestData {

  public final ArtistList artists = new ArtistList();

  public final Artist pinkFloyd = createArtist("Pink Floyd");
  public final Artist queen = createArtist("Queen");
  public final Artist deepPurple = createArtist("Deep Purple");
  public final Artist mrBig = createArtist("Mr. Big");
  public final Artist acDc = createArtist("AC/DC");
  public final Artist theCure = createArtist("The Cure", "rock");
  public final Artist pantera = createArtist("Pantera", "metal");
  public final Artist motorhead = createArtist("Mötorhead", "hard-rock");

  {
    artists.add(pinkFloyd);
    artists.add(queen);
    artists.add(deepPurple);
    artists.add(mrBig);
    artists.add(acDc);
    artists.add(theCure);
    artists.add(pantera);
    artists.add(motorhead);
  }

  private static Artist createArtist(String name, String... genres) {
    Artist a = new Artist();
    a.setId(UUID.randomUUID().toString());
    a.setName(name);
    a.setGenres(genres);
    return a;
  }

}
