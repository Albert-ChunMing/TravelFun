package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

import model.Order;
import tf.entity.Customer;
import model.CartProduct;

/**
 * Servlet implementation class AioCheckServlet
 * 1.信用卡付款時 先透過綠界處理刷卡 確定刷卡成功後再存入訂單
 * 2.貨到付款時 直接存入訂單到資料庫 並跳轉定歷史訂單
 * 3.存入訂單資料時  先存入訂單 取得資料庫訂單編號後 再存入訂單明細
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
		HttpSession session=request.getSession();		
	    		
		//產生現在時間
		LocalDateTime now=LocalDateTime.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(now.format(formatter).toString());					
		
		//將訂單資訊寫入order物件 並且把order物件放入session
		Order order = new Order();
		Customer customer=(Customer)session.getAttribute("member");
		order.setCustomerId(customer.getId());
		order.setOrderTime(now.format(formatter));
		order.setRecipient(recipient);
		order.setPhone(phone);
		order.setAddress(address);		
		order.setStatusCode("4");//先暫時設定成貨到付款				
		order.setTotalSales((Integer)session.getAttribute("totalPrice"));
		session.setAttribute("order", order);
		
		//如果付款方式為信用卡/ATM/超商條碼 跳轉到綠界處理金流
		if(cashMethod !=null && cashMethod.equals("credit")) {						
			String form=genAioCheckOutALL(request,response);
			request.setAttribute("form", form);			
			request.getRequestDispatcher("show.jsp").forward(request, response);
		}
		//如果付款方式為貨到付款 直接存入訂單 再跳轉到歷史訂單
		if(cashMethod !=null && cashMethod.equals("cod")) {
			OrderToDB.orderToDB(request, response, "4");
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
	public static String genAioCheckOutALL(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String protocol = request.getScheme();//取得通訊協定
//		URL whatismyip = new URL("http://checkip.amazonaws.com");
//		BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
//		String ipAddress =  in.readLine();//取得伺服器IP位置
//		in.close();
		String URL = protocol + "://" + "albert1986.ddns.net" + ":" + request.getLocalPort() + request.getContextPath()+"/QueryOrder.jsp";//port通訊port號碼 ContextPath為專案路徑
		String resultURL = protocol + "://" + "albert1986.ddns.net" + ":" + request.getLocalPort() + request.getContextPath()+"/CheckoutReturn";

		HttpSession session=request.getSession();
	
		all=new AllInOne("");
		List<CartProduct> list=(List<CartProduct>) session.getAttribute("productList");
		String itemName="";
		for(CartProduct p:list) {
			itemName=itemName+p.getProductName()+"*"+p.getProductQuantity()+"#";
		}		
		String amount=session.getAttribute("totalPrice").toString();		
		
		AioCheckOutALL obj = new AioCheckOutALL();
		//產生隨機單號
		UUID uid = UUID.randomUUID();
		//輸入交易單號 綠界比較 確保沒有重複單號出現
		obj.setMerchantTradeNo(uid.toString().replaceAll("-", "").substring(0, 20));
		//交易日期 刷卡必須 信用卡上的有效期限必須長於此日期
		LocalDateTime now=LocalDateTime.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		obj.setMerchantTradeDate(now.format(formatter).toString());
		//此單金額總計 需抓sessoin中totalSales
		obj.setTotalAmount(amount);
		//交易內容簡述
		obj.setTradeDesc("test Description");
		//商品名稱 多筆需用#隔開
		obj.setItemName(itemName);
		//設定回傳結果接收網址(這邊沒用到 但綠界規定要設定)
		obj.setReturnURL(URL);
		//設定回傳結果接收網址(client端)
		obj.setOrderResultURL(resultURL);
		//把session id攜帶出去 以利CheckoutReturn.java使用
		obj.setCustomField1(session.getId());
		
		String form = all.aioCheckOut(obj, null);
		return form;
				
	}
	
}
