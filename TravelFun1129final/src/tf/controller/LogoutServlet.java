package tf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tf.entity.Customer;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout.do") //url-  http://localhost:8080/vgb/logout.do
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 取得session
		HttpSession session = request.getSession(false);
		
		//2. 執行商業邏輯: 登出
		if(session!=null) {
			//為了View要的登出對話窗而保留在request範圍的logout-member
			Customer member = (Customer)session.getAttribute("member");
			if(member!=null) {
				request.setAttribute("logout-member", member);
			} 
			
			session.invalidate();	//登出		
		}
		
		//3.登出成功，server內部切換(forward)給首頁
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
