package jp.co.jri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

//name="jdbc/demo"
//auth="Container"
//type="javax.sql.DataSource"
//driverClassName="com.mysql.cj.jdbc.Driver"
//url="jdbc:mysql://127.0.0.1:3306/jripoc"
//connectionProperties="autoReconnect=true;verifyServerCertificate=false;useSSL=false;requireSSL=false"
//username="user"
//password="password"
//validationQuery="select 1"/

//@DataSourceDefinition(
//		name = "java:jdbc/jripoc", 
//		className = "com.mysql.cj.jdbc.Driver", 
//		portNumber = 3306, 
//		serverName = "127.0.0.1", 
//		databaseName = "jripoc", 
//		user = "user", 
//		password = "password")

public class DBHelper {

//	 @Resource(name="java:jdbc/jripoc")
	private DataSource ds = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public DBHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DBHelper(DataSource ds) {
		super();
		// TODO Auto-generated constructor stub
		this.ds = ds;
	}

	public Connection getCon() throws ServletException {
		try {
			// データベースへ接続
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jripoc");
			con = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServletException(e);
		}
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public PreparedStatement getPstmt(String sql) throws ServletException {
		try {
			pstmt = getCon().prepareStatement(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServletException();
		}
		return pstmt;
	}

	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}

	public ResultSet getRs(String sql) throws ServletException {
		try {
			rs = getPstmt(sql).executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServletException();
		}
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

}
