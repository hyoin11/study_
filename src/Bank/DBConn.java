package Bank;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	private Connection conn;
	
	public Connection getConnection() {
		if(conn == null) {
			String url = "jdbc:mysql://localhost:3306/test";
			String user = "root";
			String pwd = "12345678";
			try {
				conn = DriverManager.getConnection(url, user, pwd);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public void closeConnection() {
		if(conn != null) {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		conn = null;
	}
}
