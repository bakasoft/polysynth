package org.bakasoft.polysynth.testData;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

  private String name;

  private List<Track> tracks;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Track> getTracks() {
    return tracks;
  }

  public void addTrack(Track track) {
    if (tracks == null) {
      tracks = new ArrayList<>();
    }

    tracks.add(track);
  }

  public void setTracks(List<Track> tracks) {
    this.tracks = tracks;
  }
}
