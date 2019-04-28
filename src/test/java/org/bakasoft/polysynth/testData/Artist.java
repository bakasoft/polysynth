package org.bakasoft.polysynth.testData;

import java.util.UUID;

public class Artist {

  private String id;
  private String name;
  private String[] genres;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String[] getGenres() {
    return genres;
  }

  public void setGenres(String[] genres) {
    this.genres = genres;
  }

  public String getType() {
    return "artist";
  }
}
