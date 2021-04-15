package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EZshipReturn
 */
@WebServlet("/EZshipReturn")
public class EZshipReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EZshipReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s_stName = request.getParameter("stName")==null? "":request.getParameter("stName");
	    String s_stAddr = request.getParameter("stAddr")==null? "":request.getParameter("stAddr");
	    String s_webPara = request.getParameter("webPara")==null? "":request.getParameter("webPara");
	  //中文轉碼 將iso8859_1轉為UTF-8
	    s_stName = new String(s_stName.getBytes("ISO8859_1"),"UTF-8");
	    s_stAddr = new String(s_stAddr.getBytes("ISO8859_1"),"UTF-8");
	    
	    System.out.println("s_stName: "+ s_stName);
	    System.out.println("s_stAddr: "+s_stAddr);
	    System.out.println("s_webPara: "+s_webPara);
	    
	    HttpSession session=request.getSession();

	    if(!s_webPara.equals("")){
	    	System.out.println("ezship產生的session: "+session.getId());
	    	System.out.println("new session?? "+session.isNew());    	
	        //1. 把目前cookie上的session ID換成舊的session ID
	        //2. response.addCookie(cookie)後 只是變更了瀏覽器的cookie 當下的session內容還是不變
	        //3. 下次重新request時 才會讀取到舊的session內容
	        Cookie cookie=new Cookie("JSESSIONID", s_webPara);
	        cookie.setPath("/TravelFun");
	        response.addCookie(cookie);
	        
	        //1. 無法立即抓到上面剛設定好的cookie
	        //2. 舊session可以抓到cookie 新session抓不到
	        //3. 若此request是由物流或金流發起 則無法抓到對方瀏覽器中的cookie  cookie永遠只能抓自己瀏覽器中的 
	        Cookie[] cookies=request.getCookies();
	        if(cookies!=null) {
	        	for(Cookie c : cookies) {
	        		System.out.println("cookie -> "+c.getName()+" : "+c.getValue());
	        	}
	        }        	        	        
	        //parameter可完全轉交下個頁面  但還是同一次request 並無讀取舊的session
	        //request.getRequestDispatcher("Checkout.jsp").forward(request, response);
	        
	        //重新request到Checkout.jsp 可在URL後面攜帶parameter(中文參數須轉碼)
	        s_stName =URLEncoder.encode(s_stName, "UTF-8");
	        s_stAddr =URLEncoder.encode(s_stAddr, "UTF-8");
	        response.sendRedirect("Checkout.jsp?s_stName="+s_stName+"&s_stAddr="+s_stAddr);

	    	  	
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
