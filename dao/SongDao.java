package mp3.com.dao;

import java.util.ArrayList;

import mp3.com.Song;

public interface SongDao {
public abstract Song getSongById(long id);
public abstract ArrayList<Song>getAllSong();

}
