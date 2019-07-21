package mp3.com.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.print.DocFlavor.INPUT_STREAM;

import mp3.com.Song;
import mp3.com.dao.SongDao;
import mp3.com.util.db.DbUtil;

public class SongDaoImpl implements SongDao{
	public  Song getSongById(long id){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		Song song=new Song();
		try{
		conn=DbUtil.getConnection();
		String sql="select * from song where id="+id;
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		if(rs.next()){
			Long id1=rs.getLong(1);
			String  songName =rs.getString("songName");
			String  album=rs.getString("album");
			String  singer=rs.getString("singer"); 
			//InputStream songData= rs.getBinaryStream("songData");
		
		song.setAlbum(album);
		song.setSinger(singer);
		song.setSongName(songName);
		song.setId(id1);
	
		}
		
		}catch(Exception e){
			
		}finally{
			try{
			DbUtil.closeConnection(conn);
			}catch(Exception e){
				e.printStackTrace();
				}
		}
		return song;
	}
	public  ArrayList<Song>getAllSong(){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Song>list=new ArrayList<Song>();
		try{
		conn=DbUtil.getConnection();
		String sql="select * from song";
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		while(rs.next()){
			Long id=rs.getLong(1);
			String  songName =rs.getString("songName");
			String  album=rs.getString("album");
			String  singer=rs.getString("singer"); 
			InputStream songData= rs.getBinaryStream("songData");
		Song song=new Song();
		song.setAlbum(album);
		song.setSinger(singer);
		song.setSongName(songName);
		song.setId(id);
		list.add(song);
		}
		
		}catch(Exception e){
			
		}finally{
			try{
			DbUtil.closeConnection(conn);
			}catch(Exception e){
				e.printStackTrace();
				}
		}
		return list;
	}
}
