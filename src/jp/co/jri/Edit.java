package jp.co.jri;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jp.co.jri.databean.T_NOTICE_INFO;

/**
 * Servlet implementation class Edit 編集
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/jripoc")
	DataSource ds = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Edit() {
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

			// search jripoc.T_NOTICE_INFO and return
			// 発信日、発信部、件名、掲示担当者


			// Viewへ引き渡す値を設定
			String id = (String) request.getParameter("searchid");
			T_NOTICE_INFO bean = select_notice_info(id);
			request.setAttribute("bean", bean);
			request.setAttribute("formTitle", "編集画面");
			System.out.println("After set attributes");

			this.getServletContext().getRequestDispatcher("/touroku.jsp").forward(request, response);
			System.out.println("After dispatch /touroku.jsp");
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
	T_NOTICE_INFO select_notice_info(String id) throws ServletException {
		T_NOTICE_INFO bean = new T_NOTICE_INFO();
				
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// データベースへ接続
			con = ds.getConnection();

			// SQLの実行
			pstmt = con.prepareStatement(
					"select notice_id, buten_name, tanto_name, notice_title, notice_note, file_title1, file_name1, file_title2, file_name2, file_title3, file_name3, file_title4, file_name4 from jripoc.T_NOTICE_INFO where notice_id = "
							+ id);
					rs = pstmt.executeQuery();
					rs.next();

			// デーばBeanに詰める

				bean.setNOTICE_ID(rs.getInt("notice_id"));
				bean.setBUTEN_NAME(rs.getString("buten_name"));
				bean.setTANTO_NAME(rs.getString("tanto_name"));
				bean.setNOTICE_TITLE(rs.getString("notice_title"));
				bean.setNOTICE_NOTE(rs.getString("notice_note"));
				
				bean.setFILE_TITLE1(rs.getString("file_title1"));
				bean.setFILE_NAME1(rs.getString("file_name1"));
				bean.setFILE_TITLE2(rs.getString("file_title2"));
				bean.setFILE_NAME2(rs.getString("file_name2"));
				bean.setFILE_TITLE3(rs.getString("file_title3"));
				bean.setFILE_NAME3(rs.getString("file_name3"));
				bean.setFILE_TITLE4(rs.getString("file_title4"));
				bean.setFILE_NAME4(rs.getString("file_name4"));

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			}catch(Exception e) {
				e.getStackTrace();
			}
		}
		// Daoを返す
		return bean;
	}

}
