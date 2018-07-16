package jp.co.jri;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.result.UpdatableResultSet;

import jp.co.jri.databean.T_NOTICE_INFO;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/jripoc")
	DataSource ds = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// ResultSet rs = null;
		RequestDispatcher rd;
		// check login status

		String userName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user"))
					userName = cookie.getValue();
			}
		}
		if (userName == null) {
			response.sendRedirect("error.jsp");
		} else {
			System.out.println("Login OK and search DB");

			// 入力情報をDBに登録する
			T_NOTICE_INFO bean = new T_NOTICE_INFO();
			request.setCharacterEncoding("UTF-8");
			bean.setNOTICE_ID(Integer.parseInt(request.getParameter("searchid")));
			bean.setBUTEN_NAME(request.getParameter("buten_name"));
			bean.setTANTO_NAME(request.getParameter("tanto_name"));
			bean.setNOTICE_TITLE(request.getParameter("notice_title"));
			bean.setNOTICE_NOTE(request.getParameter("notice_note"));
			
			if(!update_notice_info(bean)) {
				System.out.println("Update失敗！");
				throw new ServletException();
			}
	
			request.setAttribute("formTitle", "閲覧画面");
			System.out.println("After set attributes");

			//this.getServletContext().getRequestDispatcher("Shousai").forward(request, response);
			rd = request.getRequestDispatcher("Ichiran");
			rd.forward(request, response);
			System.out.println("After dispatch /Ichian");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	
	// DBを検索し、データBeanに詰め込む
	boolean update_notice_info(T_NOTICE_INFO bean) throws ServletException {
				
		Connection con = null;
		Statement stmt = null;

		try {

			// データベースへ接続
			con = ds.getConnection();
			
			//Statement作成
			stmt = con.createStatement();

			// Update SQLの準備
			String sql = "Update T_NOTICE_INFO set buten_name='" + bean.getBUTEN_NAME() + 
					"', tanto_name='" + bean.getTANTO_NAME() +
					"', notice_title='" + bean.getNOTICE_TITLE() +
					"', notice_note='" + bean.getNOTICE_NOTE() +
					"' where notice_id='" + Integer.toString(bean.getNOTICE_ID())+"'";
			
			//Update実行
			int result;
			result = stmt.executeUpdate(sql);
			if( result == 0){
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}finally {
			try {
				stmt.close();
				con.close();
			}catch(Exception e) {
				e.getStackTrace();
			}
		}
	}

}
