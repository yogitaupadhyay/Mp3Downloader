package mp3.com.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp3.com.util.db.DbUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@WebServlet("/uploadController")
public class uploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public uploadController() {
        super();
            }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			if(ServletFileUpload.isMultipartContent(request)){
				FileItemFactory fileItemFactory=new DiskFileItemFactory();
				ServletFileUpload uploader=new ServletFileUpload(fileItemFactory);
				 response.setContentType("audio/mpeg");
				 
	         		
				 Connection conn=null;
	         		PreparedStatement pstmt=null;
	         		int rs;
	         		
				try{
				List<FileItem>fileItemList =uploader.parseRequest(request);
				FileItem fileItem1 = fileItemList.get(0);
				FileItem fileItem2 = fileItemList.get(1);
				FileItem fileItem3 = fileItemList.get(2);
				FileItem fileItem4 = fileItemList.get(3);
                 String songName=fileItem1.getString().trim();				
                 String singer=fileItem2.getString().trim();				
                 String album=fileItem3.getString().trim();				
                 InputStream sis=fileItem4.getInputStream();				
                 int size  = (int)fileItem4.getSize();
 				
                
         		conn=DbUtil.getConnection();
         		String sql="insert into song (id,songName,singer,album,songData)"
         				+ "values(song_seq.nextval,?,?,?,?)";
         		pstmt=conn.prepareStatement(sql);
         		pstmt.setString(1, songName);
         		pstmt.setString(2, singer);
         		pstmt.setString(3, album);
         		pstmt.setBinaryStream(4, sis,size);
         		rs=pstmt.executeUpdate();
         		if(rs==1){
         			String msg="file uploaded successfully ";
         		    System.out.println(msg);
   					RequestDispatcher rd = request.getRequestDispatcher("uploadForm.jsp");
					request.setAttribute("successmsg","question paper sucessfully uploaded");
					rd.forward(request,response);
         		}else{
         			RequestDispatcher rd = request.getRequestDispatcher("uploadForm.jsp");
					request.setAttribute("errormsg","error in uploading question paper");
					rd.forward(request,response);
         		}
				}catch(Exception e){
					e.printStackTrace();
					RequestDispatcher rd = request.getRequestDispatcher("uploadForm.jsp");
					request.setAttribute("errormsg","error in uploading question paper");
					rd.forward(request,response);
				}finally{
					try{
						DbUtil.closeConnection(conn);
						}catch(Exception e){
							e.printStackTrace();
							}
				}//finally end
				
			
			}//request is multipart end s here
	
	}//do post end

	

}//upload controller
