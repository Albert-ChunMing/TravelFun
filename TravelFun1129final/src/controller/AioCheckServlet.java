package controller;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

import model.Order;
import model.OrderAndDetailDAO;
import model.Orderdetail;
import tf.entity.Customer;
import model.CartProduct;

/**
 * Servlet implementation class AioCheckServlet
 * 1.先將訂單和訂單明細存入資料庫
 * 2.處理線上刷卡的部分 並將頁面轉至綠界科技結帳
 */
@WebServlet("/AioCheckServlet")
public class AioCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	public static AllInOne all;
       	String statusCode;
       	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AioCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String recipient=request.getParameter("recipient");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String delivery=request.getParameter("delivery");		
		String cashMethod=request.getParameter("cashMethod");
		String s_webPara = request.getParameter("webPara")==null?"":request.getParameter("webPara");
		HttpSession session=request.getSession();
		ServletContext context=session.getServletContext();
		
		//沒有跳轉過ezship時 刪除ServletContext中不再用到的attribute
		if(s_webPara.equals("")){ 
	    	context.removeAttribute(session.getId());	    	
	    	System.out.println("刪除ServletContext中的Attribute(AioCheckServlet)");
	    }		
		//產生現在時間
		LocalDateTime now=LocalDateTime.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(now.format(formatter));					
		
		//決定狀態碼
		if(cashMethod.equals("credit")) statusCode="2";//2為已付待出
		if(cashMethod.equals("cod")) statusCode="4";//4為待付待出
		
		//將訂單資訊寫入order物件
		Order order = new Order();
		Customer customer=(Customer)session.getAttribute("member");
		order.setCustomerId(customer.getId());
		order.setOrderTime(now.format(formatter));
		order.setRecipient(recipient);
		order.setPhone(phone);
		order.setAddress(address);		
		order.setStatusCode(statusCode);
				
		order.setTotalSales((int)session.getAttribute("totalPrice"));
		//新增訂單到資料庫
		try {
			OrderAndDetailDAO.addOrder(order);
			System.out.println("成功新增訂單");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//新增訂單明細到資料庫
		List<CartProduct> list = (List<CartProduct>) session.getAttribute("productList");
		Integer lastid = OrderAndDetailDAO.queryLastId();		
		try {
			for (CartProduct p : list) {
				//將session中的Product寫入成orderdetail物件實例
				Orderdetail detail =new Orderdetail(lastid,p);
				OrderAndDetailDAO.addOrderDetail(detail, lastid);
			}
			System.out.println("成功新增訂單明細");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//如果付款方式為信用卡/ATM/超商條碼 跳轉到綠界處理金流
		if(cashMethod !=null && cashMethod.equals("credit")) {			
			String form=genAioCheckOutALL(request,response);
			//結帳後訂單已存入資料庫 所以清空購物車
			session.removeAttribute("productList");
			session.removeAttribute("totalPrice");
			
			request.setAttribute("form", form);			
			request.getRequestDispatcher("show.jsp").forward(request, response);
		}
		//如果付款方式為貨到付款  直接顯示訂單明細
		if(cashMethod !=null && cashMethod.equals("cod")) {
			//結帳後訂單已存入資料庫 所以清空購物車
			session.removeAttribute("productList");
			session.removeAttribute("totalPrice");
			
			request.getRequestDispatcher("QueryOrder.jsp").forward(request, response);			
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	//產生表單 並且送出到頁面 直接轉跳到綠界結帳網頁
	public static String genAioCheckOutALL(HttpServletRequest request,HttpServletResponse response) throws UnknownHostException{
		String protocol = request.getScheme();//取得通訊協定
		String ipAddress =  java.net.InetAddress.getLocalHost().getHostAddress();//取得伺服器位置
		String url = protocol + "://" + ipAddress + ":" + request.getLocalPort() + request.getContextPath()+"/QueryOrder.jsp";//port通訊port號碼 ContextPath為專案路徑
		
		HttpSession session=request.getSession();
		System.out.println("AioCheckServlet session ID "+session.getId());
	
		all=new AllInOne("");
		List<CartProduct> list=(List<CartProduct>) session.getAttribute("productList");
		String itemName="";
		for(CartProduct p:list) {
			itemName=itemName+p.getProductName()+"#";
		}		
		String amount=session.getAttribute("totalPrice").toString();		
		
		AioCheckOutALL obj = new AioCheckOutALL();
		//產生隨機單號
		UUID uid = UUID.randomUUID();
		//輸入交易單號 綠界比較 確保沒有重複單號出現
		obj.setMerchantTradeNo(uid.toString().replaceAll("-", "").substring(0, 20));
		//交易日期 刷卡必須 信用卡上的有效期限必須長於此日期
		obj.setMerchantTradeDate("2017/01/01 08:05:23");
		//此單金額總計 需抓sessoin中totalSales
		obj.setTotalAmount(amount);
		//交易內容簡述
		obj.setTradeDesc("test Description");
		//商品名稱 多筆需用#隔開
		obj.setItemName(itemName);
		//將交易結果回傳到指定網址 用以做檢查碼檢查 確保資料一致
		obj.setReturnURL("http://211.23.128.214:5000");
		//設定紅利折抵內容
		obj.setNeedExtraPaidInfo("N");
		//設定回傳結果接收網址
		obj.setReturnURL(url);
		//設定交易結束後使用者導回網址
		obj.setClientBackURL(url);
		
		String form = all.aioCheckOut(obj, null);
		return form;
				
	}
	
}
