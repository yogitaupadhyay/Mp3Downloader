package mp3.com.factory;

import mp3.com.dao.SongDao;
import mp3.com.impl.SongDaoImpl;

public  class DaoFactory {
static SongDao obj;
  public static SongDao getSongDao(){
	if(obj==null){
		obj=new SongDaoImpl();
	}
	return obj;
}


}
