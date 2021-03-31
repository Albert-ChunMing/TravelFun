package tf.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tf.entity.Customer;
import tf.service.CustomerService;
import tf.entity.VGBException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns= {"/login.do"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();	
		
		//1.讀取request中的 form data並檢查
		  String id=request.getParameter("id");
		  String password =request.getParameter("password");
		  String captcha =request.getParameter("captcha");
		  //若輸入錯誤 省去使用者重新填寫的時間
		  request.setAttribute("id", id);
		  request.setAttribute("password", password);
		  HttpSession session = request.getSession();
	
		  if((id==null)||((id=id.trim()).length()==0)) 	  errors.add("必須輸入帳號");
		  if(password==null||password.length()==0) 	  errors.add("必須輸入密碼");	  
		  if(captcha==null||(captcha=captcha.trim()).length()==0) {
			  errors.add("必須輸入驗證碼");			  
		  }else {			 		
			  String sessionCaptcha = (String)session.getAttribute("CaptchaServlet");
			  if(!captcha.equalsIgnoreCase(sessionCaptcha)) {
				errors.add("驗證碼不正確");
			}
		  }
		  session.removeAttribute("CaptchaServlet");
	
		//2.若無錯誤，呼叫商業邏輯
		  if(errors.isEmpty()) {		  
			  CustomerService service = new CustomerService();
			  try {
				  Customer c =service.login(id,password); 
				  session.setAttribute("member",c); //完成登入
	
				  //若帳號密碼是管理人員則直接跳轉到後台
				  if(c.getClassname().equals("admin")) {
					  response.sendRedirect("erp.html");
					  return;
				  }
				  //3.1 登入成功則內部切換(forward)給首頁
				  request.setAttribute("member",c);//只有第一次登入成功出現的畫面..純粹畫面效果
				  request.getRequestDispatcher("/").forward(request, response);
				  return;	
				  
			  } catch (VGBException e) {
				  //e.printStackTrace();
				  this.log("登入失敗", e );//foe server端admin系統訊息
				  errors.add(e.getMessage());//for browser端給使用者的錯誤訊息
			  }catch(Exception e) {
				  this.log("登入失敗-發生非預期的錯誤", e );//for server端admin的系統訊息
				  errors.add("登入失敗-發生非預期的錯誤："+ e);
			  }
		  }		    
		    
		//3.2 登入失敗則內部切換(forward)給login.jsp
		  	request.setAttribute("errors", errors);
	    	request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
