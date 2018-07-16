package jp.co.jri;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//@DataSourceDefinition(
//name = "java:jdbc/jripoc", 
//className = "com.mysql.cj.jdbc.Driver", 
//portNumber = 3306, 
//serverName = "127.0.0.1", 
//databaseName = "jripoc", 
//user = "user", 
//password = "password")

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/LoginCheck")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/jripoc")
	DataSource ds = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		RequestDispatcher rd;
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");

		// // Viewへ引き渡す値を設定
		// request.setAttribute("rs", rs);
		//
		// // Viewを表示
		// this.getServletContext().getRequestDispatcher("/sample/views/Connect3View.jsp").forward(request,
		// response);
		try {
			if (auth(uname, password)) {
				// this.getServletContext().getRequestDispatcher("/ichiran.jsp").forward(request,
				// response);
				// request.setAttribute("login", "OK");
				Cookie c = new Cookie("user", uname);
				response.addCookie(c);

				rd = request.getRequestDispatcher("Ichiran");
				rd.forward(request, response);

			} else {
				response.sendRedirect("error.jsp");
			}
		} catch (Exception e) {
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	boolean auth(String id, String pwd) throws ServletException {



		try {
			// データソースの取得
			// Context ctx = new InitialContext();
			// DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jripoc");

			// データベースへ接続
			con = ds.getConnection();

			// SQLの実行
			pstmt = con.prepareStatement(
					"select * from jripoc.M_USER where USERID='" + id + "' and PASSWORD='" + pwd + "'");
			rs = pstmt.executeQuery();
			rs.first();

			//
			// DBHelper db = new DBHelper();
			// ResultSet rs = db.getRs("select * from jripoc.M_USER where USERID='" + id +
			// "' and PASSWORD='" + pwd + "'");
			// if (rs.getBoolean("ISVALID"))
			System.out.println(rs.getString(1));
			if (rs.getBoolean(3))
				return true;
		} catch (Exception e) {
			throw new ServletException();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
			}
		}
		return false;
	}

}
