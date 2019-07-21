package mp3.com.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp3.com.Song;
import mp3.com.util.db.DbUtil;

@WebServlet("/downloadController1")
public class downloadController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
        public downloadController1() {
        super();
           }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("audio/mpeg");
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		Long id=Long.parseLong(request.getParameter("id"));
		ServletOutputStream sos=response.getOutputStream();
		try{
		conn=DbUtil.getConnection();
		String sql="select * from song where id="+id;
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		if(rs.next()){
			String  songName =rs.getString("songName");
			String  album=rs.getString("album");
			String  singer=rs.getString("singer"); 
			InputStream songData= rs.getBinaryStream("songData");
		byte[] b=new byte[1024*1024];
		int len=0;
		response.setHeader("Content-Disposition", "attachment; filename=\""+songName+"-"+singer+"-"+album+"\"");
		while((len=songData.read(b))!=-1){
			sos.write(b, 0, len); 
		}
			sos.close();
				}
		
		}catch(Exception e){
			
		}finally{
			try{
			DbUtil.closeConnection(conn);
			}catch(Exception e){
				e.printStackTrace();
				}
		}
	}

}
