package org.bakasoft.polysynth.testData;

import java.util.ArrayList;
import java.util.List;

public class Account {

  public String user;

  public final List<Playlist> playlists;

  public Account() {
    playlists = new ArrayList<>();
  }

}
