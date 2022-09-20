package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		
		String sql = "select acc_idx from account where acc_account=2;";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println(rs.next());
			
			if(rs.next()) {
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
        	System.out.println("입금에 실패하였습니다. \n관리자에게 문의하세요.");
        	System.exit(0);
		}
	}
}
/*

sql = "UPDATE user SET user_pwd=\"" + user_pwd + "\" WHERE user_idx=\"" + user_idx + "\";";
				
				if(stmt.executeUpdate(sql) > 0) {
					System.out.println("비밀번호가 변경되었습니다.");
					System.out.println();
		            SelectType();
				}else {
					System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
		        	System.exit(0);
				}
				
				
sql = "INSERT INTO history (his_deposit, acc_idx) VALUES (" + money + ", " + user.getUserAccountIdx() + ")";

sql = "INSERT INTO history (his_withdraw, acc_idx) VALUES (" + money +", " + user.getUserAccountIdx() +")";

sql = "INSERT INTO account (acc_account, user_idx) VALUES (\"" + acc_account + "\", \"" + user_idx + "\");";
        		
        		if(stmt.executeUpdate(sql) > 0) {
        			System.out.println("회원가입이 완료되었습니다.");
        			System.out.println();
        			SelectType();
        		}
*/