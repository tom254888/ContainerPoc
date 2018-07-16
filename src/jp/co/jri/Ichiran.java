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
 * Servlet implementation class Ichiran
 */
@WebServlet("/Ichiran")
public class Ichiran extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/jripoc")
	DataSource ds = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Ichiran() {
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

		List<T_NOTICE_INFO> beans = new ArrayList<>();
		
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
			beans = select_notice_info();
			

			// Viewへ引き渡す値を設定
			request.setAttribute("beans", beans);
			request.setAttribute("formTitle", "掲示内容一覧");
			System.out.println("After set attributes");


			this.getServletContext().getRequestDispatcher("/ichiran.jsp").forward(request, response);
			System.out.println("After dispatch /ichiran.jsp");
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
	List<T_NOTICE_INFO> select_notice_info() throws ServletException {
		List<T_NOTICE_INFO> beans = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// データベースへ接続
			con = ds.getConnection();

			// SQLの実行
			pstmt = con.prepareStatement(
					"select notice_id, hasshin_day, hasshin_month, hasshin_year, buten_name, notice_title, tanto_name from jripoc.T_NOTICE_INFO ");
			rs = pstmt.executeQuery();

			// デーばBeanに詰める

			while (rs.next()) {
				T_NOTICE_INFO bean = new T_NOTICE_INFO();
				bean.setNOTICE_ID(rs.getInt("notice_id"));
				bean.setHASSHIN_DAY(rs.getString("hasshin_day"));
				bean.setHASSHIN_MONTH(rs.getString("hasshin_month"));
				bean.setHASSHIN_YEAR(rs.getString("hasshin_year"));
				bean.setBUTEN_NAME(rs.getString("buten_name"));
				bean.setNOTICE_TITLE(rs.getString("notice_title"));
				bean.setTANTO_NAME(rs.getString("tanto_name"));
				beans.add(bean);
			}

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
		return beans;
	}

	ResultSet select_notice_info2() throws ServletException {
		ResultSet rs = null;
		DBHelper db = new DBHelper();
		rs = db.getRs(
				"select notice_id, hasshin_day, hasshin_month, hasshin_year, buten_name, notice_title, tanto_name from jripoc.T_NOTICE_INFO ");
		return rs;
	}

}
