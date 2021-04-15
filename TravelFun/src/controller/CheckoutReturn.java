package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CheckoutReturn
 */
@WebServlet("/CheckoutReturn")
public class CheckoutReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String MerchantID=request.getParameter("MerchantID");//特店編號
		String MerchantTradeNo=request.getParameter("MerchantTradeNo");//特店交易編號
		String RtnCode=request.getParameter("RtnCode");//交易狀態 1為成功
		String RtnMsg=request.getParameter("RtnMsg");//交易訊息
		String TradeNo=request.getParameter("TradeNo");//綠界的交易編號
		String TradeAmt=request.getParameter("TradeAmt");//交易金額
		String TradeDate=request.getParameter("TradeDate");//訂單成立時間
		String PaymentDate=request.getParameter("PaymentDate");//付款時間
		String PaymentType=request.getParameter("PaymentType");//付款方式
		String SimulatePaid=request.getParameter("SimulatePaid");//是否為模擬付款 1是模擬 0是真的
		String CheckMacValue=request.getParameter("CheckMacValue");//檢查碼
		String CustomField1=request.getParameter("CustomField1");//自訂欄位 紀錄跳轉前的session id		
		System.out.println("特店編號: "+MerchantID+
				 "\n特店交易編號: "+MerchantTradeNo+
				 "\n交易狀態: "+RtnCode+
				 "\n交易訊息: "+RtnMsg+
				 "\n綠界交易編號: "+TradeNo+
				 "\n交易金額: "+TradeAmt+
				 "\n訂單成立時間: "+TradeDate+
				 "\n付款時間: "+PaymentDate+
				 "\n付款方式: "+PaymentType+
				 "\n模擬付款: "+SimulatePaid+
				 "\n檢查碼: "+CheckMacValue+
				 "\n舊session id: "+CustomField1
				 );
		//將cookie中的JSESSIONID 設定回原本的session
		Cookie cookie=new Cookie("JSESSIONID", CustomField1);
        cookie.setPath("/TravelFun");
        response.addCookie(cookie);
        
		if(RtnCode != null) response.sendRedirect("OrderToDB?RtnCode="+RtnCode);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
