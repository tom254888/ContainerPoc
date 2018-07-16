package com.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Connect2Controller
 */
@WebServlet("/Connect2")
public class Connect2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
//    static final String URL = "jdbc:mysql://10.211.55.6:3306/jripoc";
//    static final String URL = "jdbc:mysql://127.0.0.1:3306/jripoc";
//    static final String USERNAME = "user";
//    static final String PASSWORD = "password";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connect2Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println(" in Connect 2");
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	      // データソースの取得
	      Context ctx = new InitialContext();
	      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jripoc");
	      
//        Class.forName("com.mysql.jdbc.Driver");
//	      con = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
//        Statement statement = con.createStatement();
	 
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        
	      // データベースへ接続
	      con = ds.getConnection();
	 
	      // SQLの実行
	      pstmt = con.prepareStatement("select * from city");
	      rs = pstmt.executeQuery();
	 
	      // Viewへ引き渡す値を設定
	      rs.first();
	      request.setAttribute("ID", rs.getString("ID"));
	      request.setAttribute("Name", rs.getString("Name"));
	      request.setAttribute("CountryCode", rs.getString("CountryCode"));
	      request.setAttribute("District", rs.getString("District"));
	      request.setAttribute("Population", rs.getString("Population"));
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	      throw new ServletException(e);
	    } finally {
	      try {
	        rs.close();
	        pstmt.close();
	        con.close();
	      } catch (Exception e) {
	      }
	    }
	 
	    // Viewを表示
	    this.getServletContext().getRequestDispatcher("/sample/views/Connect2View.jsp").forward(request, response);
	  }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
