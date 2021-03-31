package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import tf.entity.Customer;
import tf.entity.VGBException;
import tf.service.*;

/**
 * Servlet Filter implementation class CheckAccount
 */
@WebFilter("/CheckAccount")
public class CheckAccount implements Filter {

	/**
	 * Default constructor.
	 */
	public CheckAccount() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		Customer member = (Customer) ((HttpServletRequest) request).getSession().getAttribute("member");
		String id = null;
		String password = null;
//		CustomersDAO cus = new CustomersDAO();
//		Customer c = null;
//		Integer totalPrice=(Integer)((HttpServletRequest)request).getSession().getAttribute("totalPrice");
		try {
			id = member.getId();
			password = member.getPassword();
//			if(totalPrice.equals(null)||totalPrice.equals(0)) {
//				System.out.print("購物車中沒有商品，請選購商品後再結帳");
//				request.setAttribute("Nothing", "請先選購商品!");
//				request.getRequestDispatcher("CartView.jsp").forward(request, response);
//				return;
//			}
		} catch (NullPointerException e) {
			System.out.print("請先登入會員!");
			request.setAttribute("Error", "結帳前請先登入會員!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
//		try {
//			c = cus.selectCustomerById(id);
//		} catch (VGBException e) {
//			System.out.print("帳號密碼錯誤請在確認,即將回到登入畫面");
//			e.printStackTrace();
//		}
			chain.doFilter(request, response);
		} 

	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
