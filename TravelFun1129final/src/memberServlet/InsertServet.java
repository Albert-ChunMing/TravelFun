package memberServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tf.entity.Customer;
import tf.entity.VGBException;
import tf.service.CustomerService;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/register.do")
public class InsertServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
    public void InsertServlet() {
    
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
		
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        //取得使用者輸入的參數  
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String password=request.getParameter("password2");         
        String gender=request.getParameter("gender");
        String email=request.getParameter("email");    
        String birthday=request.getParameter("birthday"); 
        String phone=request.getParameter("phone"); 
        String address=request.getParameter("address");
        String captcha=request.getParameter("captcha");

        //將使用者輸入的資料存在request層的attribute 省去註冊失敗時使用者重新填寫的時間
        request.setAttribute("id",id);
    	request.setAttribute("name",name);
    	request.setAttribute("password",password);
    	request.setAttribute("gender",gender);
    	request.setAttribute("email",email);
    	request.setAttribute("birthday",birthday);
    	request.setAttribute("phone",phone);
    	request.setAttribute("address",address);
    	
        //驗證碼不一致時 跳回註冊畫面重新填寫
        String sessionCaptcha = (String)session.getAttribute("CaptchaServlet");
        if(!captcha.equalsIgnoreCase(sessionCaptcha)) {
        	request.setAttribute("registerStatus", "驗證碼不一致");
        	request.getRequestDispatcher("/register.jsp").forward(request, response);
        	return;
        }
        //驗證碼相同時 進行註冊
        	Customer customer=new Customer(id,password,name);
        	customer.setGender(gender.charAt(0));
        	customer.setEmail(email);
        	customer.setBirthday(birthday);
        	customer.setPhone(phone);
        	customer.setAddress(address);
        	CustomerService service=new CustomerService();
        	try {
				service.register(customer);
			} catch (VGBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("registerStatus", e.getMessage());
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				return;
			}
        	request.setAttribute("registerStatus", "註冊成功");
        	request.getRequestDispatcher("/register.jsp").forward(request, response);

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}