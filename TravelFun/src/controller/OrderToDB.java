package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartProduct;
import model.Order;
import model.OrderAndDetailDAO;
import model.Orderdetail;

/**
 * Servlet implementation class OrderToDB
 */
@WebServlet("/OrderToDB")
public class OrderToDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Order order;
	static Integer lastid;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderToDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RtnCode=request.getParameter("RtnCode");				        
		
		//刷卡交易成功時 把訂單寫入資料庫
		if(RtnCode!=null && RtnCode.equals("1")) {
			//訂單狀態改成2 代表已付款未出貨
			orderToDB(request,response,"2");
			//將刷卡結果顯示在OrderResult.jsp
			request.setAttribute("RtnMsg", "成功");
			request.setAttribute("TradeAmt", order.getTotalSales());
			request.setAttribute("MerchantTradeNo", lastid);
		}
		
		//刷卡交易失敗時 
		if(RtnCode==null || !RtnCode.equals("1")) {
			//將刷卡結果顯示在OrderResult.jsp
			request.setAttribute("RtnMsg", "失敗");
			request.setAttribute("TradeAmt", 0);
			request.setAttribute("MerchantTradeNo", "訂單未成立");
		}
		
		request.getRequestDispatcher("OrderResult.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected static void orderToDB(HttpServletRequest request, HttpServletResponse response,String statusCode) throws ServletException, IOException {
		HttpSession session=request.getSession();
		order=(Order)session.getAttribute("order");
		List<CartProduct> list = (List<CartProduct>) session.getAttribute("productList");
		//新增訂單到資料庫
		try {
			order.setStatusCode(statusCode);
			OrderAndDetailDAO.addOrder(order);
			System.out.println("成功新增訂單");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//新增訂單明細到資料庫
		lastid = OrderAndDetailDAO.queryLastId();
		try {
			for (CartProduct p : list) {
				//將session中的Product寫入成orderdetail物件實例
				Orderdetail detail =new Orderdetail(lastid,p);
				OrderAndDetailDAO.addOrderDetail(detail, lastid);
			}
			System.out.println("成功新增訂單明細");			
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
		//結帳後訂單已存入資料庫 所以清空session中訂單相關的attribute 只留下member
		Collections.list(session.getAttributeNames()).stream().filter( s->!s.equals("member")).forEach( s->session.removeAttribute(s));
	}

}
