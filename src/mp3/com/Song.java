package mp3.com;

public class Song {
String songName;
String album;
String singer;
long id;


/**
 * @return the id
 */
public long getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(long id) {
	this.id = id;
}

/**
 * @return the songName
 */
public String getSongName() {
	return songName;
}

/**
 * @param songName the songName to set
 */
public void setSongName(String songName) {
	this.songName = songName;
}

public String getAlbum() {
	return album;
}

public void setAlbum(String album) {
	this.album = album;
}

public String getSinger() {
	return singer;
}

public void setSinger(String singer) {
	this.singer = singer;
}

}
