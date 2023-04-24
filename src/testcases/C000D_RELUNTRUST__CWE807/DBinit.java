package testcases.C000D_RELUNTRUST;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBinit {

	public void initMSSQLDB() {
		Statement st=null;
		Connection con=null;
	
		con = getConnection(this);	
		try {
		st = con.createStatement();
		st.execute("drop table board_member");
		st.execute("CREATE TABLE board_member (IDX int IDENTITY(1,1) PRIMARY KEY,USERID VARCHAR(70) NOT NULL,USERPW VARCHAR(50) NOT NULL,USERNAME VARCHAR(50) NOT NULL,JOINDATE DATETIME );");
		st.execute("insert into board_member(userid,userpw,username,joindate) values('admin','1234','관리자',getdate());");
		st.execute("insert into board_member(userid,userpw,username,joindate) values('test','test','테스터',getdate());");
		} catch(SQLException e){
			System.out.println("MS SQL DB 초기화 오류 발생");
		}
		if ( st != null ) try { st.close(); }catch(SQLException e){}
		if ( con != null ) try { con.close(); }catch(SQLException e){}
	}
		
	public void initMySQLDB() {
		Statement st=null;;
		Connection con=null;
	
		con = getConnection(this);	
		try {
		st = con.createStatement();
//		drop table board;
//		drop table board_member;
//		drop table board_comment;
//		drop table openeg_security;
//		drop table login_history;
//		CREATE TABLE board_member (IDX INT not null auto_increment,USERID VARCHAR(70) NOT NULL,USERPW VARCHAR(50) NOT NULL,USERNAME VARCHAR(50) NOT NULL,PINNO VARCHAR(15),JOINDATE DATE,PRIMARY KEY (IDX));
//		CREATE TABLE board_comment (IDX INT primary key auto_increment,	WRITER VARCHAR(50) NOT NULL,CONTENT VARCHAR(500) NOT NULL,WRITEDATE DATE NOT NULL,	LINKEDARTICLENUM INT NOT NULL,WRITERID VARCHAR(50) NOT NULL);
//		CREATE TABLE board (IDX INT primary key auto_increment,	WRITER VARCHAR(50) NOT NULL,SUBJECT VARCHAR(150) NOT NULL,CONTENT VARCHAR(500) NOT NULL,	HITCOUNT INT NOT NULL,RECOMMENDCOUNT INT NOT NULL,WRITEDATE DATE NOT NULL,WRITERID VARCHAR(50) NOT NULL,FILENAME VARCHAR(100), SAVEDFILENAME VARCHAR(100));
//		CREATE TABLE openeg_security (userid varchar(70) primary key,salt varchar(70), seckey varchar(120));
//		CREATE TABLE login_history (IDX INT primary key auto_increment,userid varchar(70) ,retry int,lastFailedLogin bigint,lastSuccessedLogin bigint,clientIp varchar(15));
//		INSERT INTO board_member(USERID,USERPW,USERNAME,PINNO,JOINDATE) VALUES('admin','openeg','관리자','123456-1234567',now());
//		INSERT INTO board_member(USERID,USERPW,USERNAME,PINNO,JOINDATE) VALUES('test','test','테스트','654321-2345678',now());
//		INSERT INTO openeg_security(USERID) VALUES('admin');
//		INSERT INTO openeg_security(USERID) VALUES('test');
		st.execute("drop table board");
		st.execute("drop table board_member");
		st.execute("drop table board_comment");
		st.execute("drop table openeg_security");
		st.execute("drop table login_history");
		st.execute("CREATE TABLE board_member (IDX INT not null auto_increment,USERID VARCHAR(70) NOT NULL,USERPW VARCHAR(50) NOT NULL,USERNAME VARCHAR(50) NOT NULL,PINNO VARCHAR(15),JOINDATE DATE,PRIMARY KEY (IDX));");
		st.execute("CREATE TABLE board_comment (IDX INT primary key auto_increment,	WRITER VARCHAR(50) NOT NULL,CONTENT VARCHAR(500) NOT NULL,WRITEDATE DATE NOT NULL,	LINKEDARTICLENUM INT NOT NULL,WRITERID VARCHAR(50) NOT NULL);");
		st.execute("CREATE TABLE board (IDX INT primary key auto_increment,	WRITER VARCHAR(50) NOT NULL,SUBJECT VARCHAR(150) NOT NULL,CONTENT VARCHAR(500) NOT NULL,	HITCOUNT INT NOT NULL,RECOMMENDCOUNT INT NOT NULL,WRITEDATE DATE NOT NULL,WRITERID VARCHAR(50) NOT NULL,FILENAME VARCHAR(100), SAVEDFILENAME VARCHAR(100));");
		st.execute("CREATE TABLE openeg_security (userid varchar(70) primary key,salt varchar(70), seckey varchar(120))");
		st.execute("CREATE TABLE login_history (IDX INT primary key auto_increment,userid varchar(70),retry int,lastFailedLogin bigint,lastSuccessedLogin bigint,clientIp varchar(15));");
		st.execute("INSERT INTO board_member(USERID,USERPW,USERNAME,PINNO,JOINDATE) VALUES('admin','openeg','관리자','123456-1234567',now())");
		st.execute("INSERT INTO board_member(USERID,USERPW,USERNAME,PINNO,JOINDATE) VALUES('test','test','테스트','654321-2345678',now())");
		st.execute("INSERT INTO openeg_security(USERID) VALUES('admin')");
		st.execute("INSERT INTO openeg_security(USERID) VALUES('test')");
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println("MySQL DB 초기화 오류 발생");
		}
		if ( st != null ) try { st.close(); }catch(SQLException e){}
		if ( con != null ) try { con.close(); }catch(SQLException e){}
	}
	
	public static Connection getConnection(Object o) {
	   	 Connection con=null;
	   	 Properties properties = new Properties();
	   	 
	   	 try {   		 
	   	      properties.load( o.getClass().getClassLoader()
	   	    		               .getResourceAsStream("config/dbconnMs.properties"));
	   	 } catch (IOException e) {
	   		System.out.println("DB 정보 로드 실패");
	   	    return null;
	   	 }
	   	 String driver = properties.getProperty("jdbc.driver");
	   	 String url = properties.getProperty("jdbc.url");
	   	 String username = properties.getProperty("jdbc.username");
	   	 String password = properties.getProperty("jdbc.password");
	   
	   	 try {
	   	    Class.forName(driver); 
	   	    con=DriverManager.getConnection(url,username,password);
	   	 }catch(Exception e){
	   		 System.out.println("DB 연결 오류");
	   		 return null;
	   	 } 
	   	 return con;
	    }
}
