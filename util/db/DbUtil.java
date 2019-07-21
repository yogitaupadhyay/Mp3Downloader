package mp3.com.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	  //load driver
		private final static String DRIVER_CLASS="oracle.jdbc.driver.OracleDriver";
		private final static String URL ="jdbc:oracle:thin:@localhost:1521:XE";
		private final static String USER ="system";
		private final static String PASSWORD ="Ig32mind$";
		private static boolean  isDriverLoaded=false;
		
		static {
			try {
				Class.forName(DRIVER_CLASS);
				System.out.println("driver loaded");
				isDriverLoaded=true;
			} catch (ClassNotFoundException e) {
				System.out.println("ERROR : driver Class Not Found " + DRIVER_CLASS);
			}
			
		}//end static block
		
		
		public static Connection getConnection() throws SQLException{
			Connection con = null;
			 if(isDriverLoaded) {
				 con  = DriverManager.getConnection(URL,USER,PASSWORD);
			 }
		return con;
		}
		
		//close connection
		
		public static void closeConnection(Connection con) throws SQLException {
			
			if(con!=null) {
				con.close();
			}
			
		}
		
}
