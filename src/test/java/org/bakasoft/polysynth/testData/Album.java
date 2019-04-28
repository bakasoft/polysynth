package org.bakasoft.polysynth.testData;

import java.time.LocalDate;

public class Album {

  public String id;

  public String name;

  public AlbumType albumType;

  public LocalDate releaseDate;

  public Integer popularity;

  public Artist[] artists;

  public String[] genres;

  public Track[] tracks;

}
