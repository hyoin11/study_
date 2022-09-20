package Bank;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	public static void main(String[] args) {
		try {
			Bank obj = new Bank();
			obj.run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void run(String[] args) throws Exception {
		System.out.println("-----BANK-----");
		SelectType();
	}

	// 선택
	public void SelectType(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1.로그인 2.회원가입 3.아이디찾기 4.비밀번호찾기 0.종료");
        String type = sc.nextLine();
        switch(type){
            case "1":
                LogIn();
                break;
            case "2":
                SignUp();
                break;
            case "3":
                FindId();
                break;
            case "4":
                FindPw();
                break;
            case "0":
                System.out.println("종료되었습니다.");
                System.exit(0);
                break;
            default:
                System.out.println("0~4 사이로 입력하세요.\n");
                System.out.println();
                SelectType();
                break;
        }
        sc.close();
    }
	
	// 회원가입
    public void SignUp(){
    	DBConn db = new DBConn();
    	Connection conn = db.getConnection();
    	Statement stmt;
    	ResultSet rs;
    	String sql;
    	
        System.out.println("-----회원가입-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("사용할 아이디를 입력하세요.");
        String user_id = null;
        boolean id_dup = true;
        
        while(id_dup) {
        	user_id = sc.nextLine();
        	
        	sql = "SELECT user_idx FROM user WHERE user_id=\"" + user_id +"\";";
        	
        	try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				if(rs.next()) {
					System.out.println("사용중인 아이디입니다. \n다시 입력해 주세요.");
				}else {
					System.out.println("사용가능한 아이디입니다.");
					id_dup = false;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
        }
        System.out.println("사용할 비밀번호를 입력하세요.");
        String user_pwd = sc.nextLine();
        System.out.println("이름을 입력하세요.");
        String user_name = sc.nextLine();
        System.out.println("생년월일을 입력하세요. (예. 19910101)");
        String user_birthday = sc.nextLine();
        System.out.println("전화번호를 입력하세요. (예. 01012345678)");
        String user_phone = sc.nextLine();
        
        sql = "INSERT INTO user (user_id, user_pwd, user_name, user_birthday, user_phone)"
        		+ "VALUES(\"" + user_id + "\", \"" + user_pwd + "\", \"" + user_name + "\", \"" + user_birthday + "\", \"" + user_phone + "\");";
        try {
        	conn.setAutoCommit(false);
        	
        	stmt = conn.createStatement();
        	
        	stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        	
        	rs = stmt.getGeneratedKeys();
        	
        	if(rs.next()) {
        		String user_idx = String.valueOf(rs.getInt(1));
        		
        		// 계좌번호생성
        		boolean acc_dup = true;
        		int acc_account = 0;
        		
        		while(acc_dup) {
        			acc_account = (int) Math.round(Math.random() *100000);
        			
        			sql = "SELECT acc_idx FROM account WHERE acc_account=\"" + acc_account + "\";";
        			
        			rs = stmt.executeQuery(sql);
        			
        			if(!rs.next()) {
        				acc_dup = false;
        			}
        		}
        		
        		sql = "INSERT INTO account (acc_account, user_idx) VALUES (\"" + acc_account + "\", \"" + user_idx + "\");";
        		
        		if(stmt.executeUpdate(sql) > 0) {
        			conn.commit();
        			System.out.println("회원가입이 완료되었습니다.");
        			System.out.println();
        			SelectType();
        		}else {
        			conn.rollback();
        			System.out.println("회원가입이 실패하였습니다. \n관리자에게 문의하세요.");
                    System.exit(0);
        		}
			}else {
				conn.rollback();
				System.out.println("회원가입이 실패하였습니다. \n관리자에게 문의하세요.");
                System.exit(0);
			}
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("회원가입이 실패하였습니다. \n관리자에게 문의하세요.");
        	System.exit(0);
        }
        SelectType();
        sc.close();
    }
    
    // 로그인
    public void LogIn(){
    	DBConn db = new DBConn();
    	Connection conn = db.getConnection();
    	Statement stmt;
    	ResultSet rs;
    	
        System.out.println("-----로그인-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("아이디를 입력하세요.");
        String user_id = sc.nextLine();
        System.out.println("비밀번호를 입력하세요.");
        String user_pwd = sc.nextLine();
        
        try {
        	String sql = "SELECT u.user_idx, u.user_name, a.acc_idx, a.acc_account, a.acc_cash "
        	+ "FROM user AS u JOIN account AS a ON u.user_idx = a.user_idx "
        	+ "WHERE u.user_id=\"" + user_id + "\" AND u.user_pwd=\"" + user_pwd + "\"";
        	
        	stmt = conn.createStatement();
        	rs = stmt.executeQuery(sql);
        	
        	if(rs.next()) {
				System.out.println("로그인 되었습니다.");
				
				int user_idx = rs.getInt("u.user_idx");
				String user_name = rs.getString("u.user_name");
				int user_account_idx = rs.getInt("a.acc_idx");
				int user_account = rs.getInt("a.acc_account");
				int user_cash = rs.getInt("a.acc_cash");
				
				User user = new User();
				user.setUserIdx(user_idx);
				user.setUserId(user_id);
				user.setUserPwd(user_pwd);
				user.setUserName(user_name);
				user.setUserAccountIdx(user_account_idx);
				user.setUserAccount(user_account);
				user.setUserCash(user_cash);
				
	            myMain(user);
			}else {
				System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
				System.out.println();
	            SelectType();
			}
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
        	System.exit(0);
        }
        sc.close();
    }
    
    // 아이디 찾기
    public void FindId(){
    	DBConn db = new DBConn();
    	Connection conn = db.getConnection();
    	Statement stmt;
    	ResultSet rs;
    	ArrayList<String[]> id_arr = new ArrayList<>();
    	
        System.out.println("-----아이디 찾기-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("이름을 입력하세요.");
        String user_name = sc.nextLine();
        System.out.println("전화번호를 입력하세요.");
        String user_phone = sc.nextLine();
        
        try {
        	String sql = "SELECT user_id, user_regdate FROM user WHERE user_name=\"" + user_name +"\" AND user_phone=\"" + user_phone + "\" ORDER BY user_regdate DESC;";
    		
        	stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String get_user_id = rs.getString("user_id");
				String get_user_regdate = rs.getString("user_regdate").substring(0, 11).replace("-", ".");
				String[] temp = new String[2];
				temp[0] = get_user_id;
				temp[1] = get_user_regdate;
				id_arr.add(temp);
			}
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
        	System.exit(0);
        }

        if(id_arr.size() > 0) {
			System.out.println("고객님의 정보와 일치하는 아이디 목록입니다.");
			System.out.println();
			for(int i=0; i<id_arr.size(); i++) {
				System.out.println(id_arr.get(i)[0] + "\t가입 : " + id_arr.get(i)[1]);
			}
			System.out.println();
            SelectType();
		}else {
			System.out.println("일치하는 아이디가 없습니다.");
			System.out.println();
            SelectType();
		}
        sc.close();
    }
    
    // 비밀번호 찾기
    public void FindPw(){
    	DBConn db = new DBConn();
    	Connection conn = db.getConnection();
    	Statement stmt;
    	ResultSet rs;
    	String sql;
    	
        System.out.println("-----비밀번호 찾기-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("아이디를 입력하세요.");
        String user_id = sc.nextLine();
        System.out.println("전화번호를 입력하세요.");
        String user_phone = sc.nextLine();
        
        sql = "SELECT user_idx FROM user WHERE user_id=\"" + user_id +"\" AND user_phone=\"" + user_phone + "\";";
        try {
        	stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				String user_idx = rs.getString("user_idx");
				
				System.out.println("변경할 비밀번호를 입력하세요.");
				String user_pwd = sc.nextLine();
				
				sql = "UPDATE user SET user_pwd=\"" + user_pwd + "\" WHERE user_idx=\"" + user_idx + "\";";
				
				if(stmt.executeUpdate(sql) > 0) {
					System.out.println("비밀번호가 변경되었습니다.");
					System.out.println();
		            SelectType();
				}else {
					System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
		        	System.exit(0);
				}
			}else {
				System.out.println("아이디 또는 전화번호가 틀렸습니다.");
				System.out.println();
	            SelectType();
			}
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
        	System.exit(0);
        }
        
        sc.close();
    }

    
    
    
    
    // 로그인 후 메인
    public void myMain(User user) {
    	/*
    	 * 고객 정보로 계좌번호, 잔액 보여주기
    	 */
    	System.out.println("-----main-----");
    	System.out.println(user.getUserName() + "님");
    	System.out.println("계좌번호 " + user.getUserAccount());
    	System.out.println("잔액 " + user.getUserCash());
    	
    	Scanner sc = new Scanner(System.in);
        System.out.println("1.입금 2.출금 3.송금 4.거래내역조회 5.내정보수정 0.종료");
        String type = sc.nextLine();
        switch(type){
            case "1":
            	Deposit(user);
            	break;
            case "2":
                Withdraw(user);
                break;
            case "3":
                Transfer(user);
                break;
            case "4":
            	History(user);
            	break;
            case "5":
            	Modify(user);
            	break;
            case "0":
                System.out.println("종료되었습니다.");
                System.exit(0);
                break;
            default:
                System.out.println("0~5 사이로 입력하세요.\n");
                System.out.println();
                myMain(user);
                break;
        }
        sc.close();
    }
    
    // 입금
    public void Deposit(User user) {
    	DBConn db = new DBConn();
    	Connection conn = db.getConnection();
    	Statement stmt;
    	String sql;

    	Scanner sc;
    	int money;
    	System.out.println("-----입금-----");
    	
    	while(true) {
    		sc = new Scanner(System.in);
    		System.out.println("입금하실 금액을 입력해주세요.");
            money = Integer.parseInt(sc.nextLine());
            
            sql = "INSERT INTO history (his_deposit, acc_idx) VALUES (" + money + ", " + user.getUserAccountIdx() + ")";
            
            try {
            	conn.setAutoCommit(false);
            	
            	stmt = conn.createStatement();
            	if(stmt.executeUpdate(sql) > 0) {
            		sql = "UPDATE account SET acc_cash=acc_cash+" + money + " WHERE acc_idx=" + user.getUserAccountIdx();
            		
            		if(stmt.executeUpdate(sql) > 0) {
            			conn.commit();
            			user.setUserCash(user.getUserCash() + money);
            			System.out.println("입금이 완료되었습니다. \n추가로 입금하시겠습니까? Y/N");
            			String answer = sc.nextLine();
            			
            			if(answer.equals("Y") || answer.equals("y")) continue;
            			else if(answer.equals("N") || answer.equals("n")) {
            				break;
            			}else {
            				System.out.println("잘못입력하셨습니다.");
            				break;
            			}
            		}else {
            			conn.rollback();
            			System.out.println("입금에 실패하였습니다. \n관리자에게 문의하세요.");
    	                System.exit(0);
            		}
            	}else {
            		conn.rollback();
            		System.out.println("입금에 실패하였습니다. \n관리자에게 문의하세요.");
                    System.exit(0);
            	}
            }catch(Exception e) {
            	e.printStackTrace();
            	System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
            	System.exit(0);
            }
            sc.close();
    	}
		myMain(user);
    }
    
    // 출금
    public void Withdraw(User user) {
    	DBConn db = new DBConn();
    	Connection conn = db.getConnection();
    	Statement stmt;
    	String sql;

    	Scanner sc;
    	int money;
    	
    	while(true) {
    		System.out.println("-----출금-----");
    		sc = new Scanner(System.in);
    		System.out.println("출금하실 금액을 입력해주세요.");
            money = Integer.parseInt(sc.nextLine());
            
            if(money > user.getUserCash()) {
            	System.out.println("보유금액보다 큰 금액은 출금하실 수 없습니다.");
            	break;
            }
            
            sql = "INSERT INTO history (his_withdraw, acc_idx) VALUES (" + money +", " + user.getUserAccountIdx() +")";
            
            try {
            	conn.setAutoCommit(false);
            	
            	stmt = conn.createStatement();
            	if(stmt.executeUpdate(sql) > 0) {
            		sql = "UPDATE account SET acc_cash=acc_cash-" + money +" WHERE acc_idx=" + user.getUserAccountIdx();
        		
	        		if(stmt.executeUpdate(sql) > 0) {
	        			conn.commit();
	        			user.setUserCash(user.getUserCash() - money);
            			System.out.println("출금이 완료되었습니다.");
            			System.out.println("잔액 " + user.getUserCash());
            			System.out.println("추가로 입금하시겠습니까? Y/N");
            			String answer = sc.nextLine();
            			
            			if(answer.equals("Y") || answer.equals("y")) continue;
            			else if(answer.equals("N") || answer.equals("n")){
            				break;
            			}else {
            				System.out.println("잘못입력하셨습니다.");
            				break;
            			}
	        		}else {
	        			conn.rollback();
						System.out.println("출금에 실패하였습니다. \n관리자에게 문의하세요.");
		                System.exit(0);
					}
            	}else {
            		conn.rollback();
					System.out.println("출금에 실패하였습니다. \n관리자에게 문의하세요.");
	                System.exit(0);
				}
            	
            }catch(Exception e) {
            	e.printStackTrace();
            	System.out.println("출금에 실패하였습니다. \n관리자에게 문의하세요.");
            	System.exit(0);
            }
            sc.close();
    	}
    	myMain(user);
    }
    
    // 송금
    public void Transfer(User user) {
    	DBConn db = new DBConn();
    	Connection conn = db.getConnection();
    	Statement stmt;
    	ResultSet rs;
    	String sql = "";
    	
    	Scanner sc;
    	String transfer_account;
    	int transfer_idx;
    	int money;
    	System.out.println("-----송금-----");
    	sc = new Scanner(System.in);
    	
    	while(true) {
    		System.out.println("송금하실 계좌번호를 입력해주세요.");
    		transfer_account = sc.nextLine();
    		
    		sql = "SELECT acc_idx FROM account WHERE acc_account=" + transfer_account + ";";
    		
    		try {
    			stmt = conn.createStatement();
    			rs = stmt.executeQuery(sql);
    			
    			if(!rs.next()) {
    				// 계좌 없을 경우
    				System.out.println("계좌번호 입력 오류입니다. \n계좌번호를 확인해주세요.");
    				continue;
    			}else {
    				// 계좌 있을 경우
    				transfer_idx = rs.getInt("acc_idx");
    				
    				break;
    			}
    		}catch(Exception e) {
    			e.printStackTrace();
            	System.out.println("송금에 실패하였습니다. \n관리자에게 문의하세요.");
            	System.exit(0);
    		}
    	}
    	
    	while(true) {
    		System.out.println("송금하실 금액을 입력해주세요.");
			money = Integer.parseInt(sc.nextLine());
			
			if(money > user.getUserCash()) {
				System.out.println("보유금액보다 큰 금액은 출금하실 수 없습니다.");
				continue;
			}else {
				break;
			}
    	}
    	
    	try {
    		conn.setAutoCommit(false);
    		
    		stmt = conn.createStatement();
    		
    		sql = "INSERT INTO history (his_withdraw, acc_idx) VALUES (" + money + ", " + user.getUserAccountIdx() + ");";
    		
    		if(stmt.executeUpdate(sql) <= 0) {
    			System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
	        	System.exit(0);
    		}
    		
    		sql = "INSERT INTO history (his_deposit, acc_idx) VALUES (" + money + ", " + transfer_idx + ");";
    		
    		if(stmt.executeUpdate(sql) <= 0) {
    			conn.rollback();
    			System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
	        	System.exit(0);
    		}
    		
    		sql = "UPDATE account SET acc_cash=acc_cash-" + money + " WHERE acc_idx=" + user.getUserAccountIdx() + ";";
    		
    		if(stmt.executeUpdate(sql) <= 0) {
    			conn.rollback();
    			System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
	        	System.exit(0);
    		}
    		user.setUserCash(user.getUserCash() - money);
    		
    		sql = "UPDATE account SET acc_cash=acc_cash+" + money + " WHERE acc_account=" + transfer_idx + ";";
    		if(stmt.executeUpdate(sql) <=0) {
    			conn.rollback();
    			user.setUserCash(user.getUserCash() + money);
    			System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
	        	System.exit(0);
    		}
    		
    		conn.commit();
    		System.out.println("송금이 완료되었습니다.");
    		myMain(user);
    	}catch(Exception e) {
			e.printStackTrace();
        	System.out.println("송금에 실패하였습니다. \n관리자에게 문의하세요.");
        	System.exit(0);
		}
    	
        sc.close();
    }
    
    // 거래내역조회
    public void History(User user) {
    	DBConn db = new DBConn();
    	Connection conn = db.getConnection();
    	Statement stmt;
    	ResultSet rs;
    	String sql = "";
    	
    	Scanner sc;
    	String type;
    	ArrayList<String[]> history_list = new ArrayList<>();
    	System.out.println("-----거래내역조회-----");
    	sc = new Scanner(System.in);
    	System.out.println("1.입급내역 2.출금내역 3.전채내역");
        type = sc.nextLine();
        
        if(type.equals("1")) {
        	String[] temp = new String[2];
        	temp[0] = "입금내역";
        	temp[1] = "날짜";
        	history_list.add(temp);
        	sql = "SELECT his_deposit, his_date FROM history WHERE acc_idx=" + user.getUserAccountIdx() + " AND his_deposit IS NOT NULL ORDER BY his_date DESC;";
        }else if(type.equals("2")) {
        	String[] temp = new String[2];
        	temp[0] = "출금내역";
        	temp[1] = "날짜";
        	history_list.add(temp);
        	sql = "SELECT his_withdraw, his_date FROM history WHERE acc_idx=" + user.getUserAccountIdx() + " AND his_withdraw IS NOT NULL ORDER BY his_date DESC;";
        }else if(type.equals("3")) {
        	String[] temp = new String[3];
        	temp[0] = "입금내역";
        	temp[1] = "출금내역";
        	temp[2] = "날짜";
        	history_list.add(temp);
        	sql = "SELECT his_deposit, his_withdraw, his_date FROM history WHERE acc_idx=" + user.getUserAccountIdx() + " ORDER BY his_date DESC;";
        }else {
        	System.out.println("1~3 사이로 입력하세요.");
            System.out.println();
            History(user);
        }
        
        try {
        	stmt = conn.createStatement();
        	rs = stmt.executeQuery(sql);
        	
        	String deposit_data = "";
        	String withdraw_data = "";
        	String history_date = "";
        	
        	if(type.equals("1")) {
        		type = "-----입금내역-----";
	        	
        		while(rs.next()) {
        			String[] temp = new String[2];
        			deposit_data = rs.getString("his_deposit");
    	        	history_date = rs.getString("his_date");
    	        	
    	        	temp[0] = deposit_data;
    	        	temp[1] = history_date;
    	        	
    	        	history_list.add(temp);
        		}
            }else if(type.equals("2")) {
            	type = "-----출금내역-----";
	        	
            	while(rs.next()) {
            		String[] temp = new String[2];
            		withdraw_data = rs.getString("his_withdraw");
    	        	history_date = rs.getString("his_date");
    	        	
    	        	temp[0] = withdraw_data;
    	        	temp[1] = history_date;
    	        	
    	        	history_list.add(temp);
        		}
            }else if(type.equals("3")) {
            	type = "-----전체내역-----";
	        	
            	while(rs.next()) {
            		String[] temp = new String[3];
            		deposit_data = rs.getString("his_deposit");
    	        	withdraw_data = rs.getString("his_withdraw");
    	        	history_date = rs.getString("his_date");
    	        	
    	        	
    	        	temp[0] = deposit_data;
    	        	temp[1] = withdraw_data;
    	        	temp[2] = history_date;
    	        	
    	        	history_list.add(temp);
        		}
            }
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
        	System.exit(0);
        }
        
        if(history_list.size() <= 1) {
        	System.out.println(type.substring(5,9) + "이 없습니다.");
        }else {
        	System.out.println(type);
        	for(String[] history : history_list) {
        		for(int i=0; i<history.length; i++) {
        			System.out.print((history[i] == null ? "-" : history[i]) + "\t");
             	}
             	System.out.println();
        	}
        }
       
        
        System.out.println();
        myMain(user);
        
        sc.close();
    }
    
    // 정보수정
    public void Modify(User user) {
    	DBConn db = new DBConn();
    	Connection conn = db.getConnection();
    	Statement stmt;
    	String sql = "";
    	
    	Scanner sc;
    	String type;
    	String user_pwd = "";
    	String user_phone = "";
    	System.out.println("-----내정보수정-----");
    	sc = new Scanner(System.in);
    	System.out.println("1.비밀번호변경 2.전화번호변경");
        type = sc.nextLine();
        
        if(type.equals("1")) {
        	System.out.println("변경할 비밀번호를 입력하세요.");
			user_pwd = sc.nextLine();
			
			sql = "UPDATE user SET user_pwd=\"" + user_pwd + "\" WHERE user_idx=\"" + user.getUserIdx() + "\";";
        }else if(type.equals("2")) {
        	System.out.println("변경할 전화번호를 입력하세요.");
			user_phone = sc.nextLine();
			
			sql = "UPDATE user SET user_pwd=\"" + user_phone + "\" WHERE user_idx=\"" + user.getUserIdx() + "\";";
        }
        
        try {
        	stmt = conn.createStatement();
        	
        	if(stmt.executeUpdate(sql) > 0) {
        		if(type.equals("1")) {
        			System.out.println("비밀번호가 변경되었습니다.");
        			user.setUserPwd(user_pwd);
                }else if(type.equals("2")) {
                	System.out.println("전화번호가 변경되었습니다.");
        			user.setUserPhone(user_phone);
                }
				System.out.println();
	            myMain(user);
			}else {
				System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
	        	System.exit(0);
			}
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("오류가 발생하였습니다. \n관리자에게 문의하세요.");
        	System.exit(0);
        }
        
    	sc.close();
    }
}

