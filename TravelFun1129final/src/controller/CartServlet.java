package controller;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.CartProduct;
import model.ProductDAO;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       

    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");		
		String name=request.getParameter("name");
		String color=request.getParameter("color");
		String size=request.getParameter("size");
		String quantity=request.getParameter("quantity");		
		String execute=request.getParameter("execute");
		String productForDelete=request.getParameter("productForDelete");
		String addOne=request.getParameter("addOne");
		String minusOne=request.getParameter("minusOne");
		Gson gson=new Gson();
		NumberFormat Formatter = NumberFormat.getNumberInstance();
		HttpSession session=request.getSession();
		List<CartProduct> productList=new ArrayList();

		//轉跳到購物頁面時顯示當前購物車內的商品數量
		if(execute !=null && execute.equals("cartDetail")){								
			if(session.getAttribute("productList")==null) {						
				session.setAttribute("productList", productList);
			}
			else {
				productList=(List<CartProduct>)session.getAttribute("productList");						
			}								
			response.getWriter().print(productList.size());
			return;			
		}
		
		//加到購物車
		if(execute !=null && execute.equals("add")){
			//產生Product p以儲存商品訂購資訊
			CartProduct p=ProductDAO.getProductByName(name);
			p.setProductColor(color);
			p.setProductSize(size);
			p.setProductQuantity(Integer.parseInt(quantity));
			ProductDAO.setPhotoURL(name, color, p);
			ProductDAO.setProductItemNumber(name, color, size, p);
			//確認session的attribute是否有productList 沒有的話建立新的 有的話叫出來用
			if(session.getAttribute("productList")==null) {						
				session.setAttribute("productList", productList);
			}
			else {
				productList=(List<CartProduct>)session.getAttribute("productList");						
			}			
			//若購物清單存在且已有該商品(名稱顏色尺寸皆相同) 則增加該商品數量					
			for(CartProduct oldp:productList) {
				if(oldp.getProductName().equals(p.getProductName()) 
						&& oldp.getProductColor().equals(p.getProductColor()) 
						&& oldp.getProductSize().equals(p.getProductSize())) {
					int i=oldp.getProductQuantity()+p.getProductQuantity();
					oldp.setProductQuantity(i);
					response.getWriter().print(productList.size());
					totalPrice(request);
					return;					
				}				
			}
			productList.add(p);											
			response.getWriter().print(productList.size());
			totalPrice(request);
			return;			
		}		
		
		//利用名稱 顏色 尺寸 刪除某項商品
		if(productForDelete !=null){
			//把待刪除商品的資料(名稱顏色尺寸)從Json字串轉成Product物件			
			CartProduct pfd=gson.fromJson(productForDelete,CartProduct.class);
			productList=(List<CartProduct>)session.getAttribute("productList");
			//和productList比對 找出符合的商品
			for(CartProduct p:productList){
				if(p.getProductName().equals(pfd.getProductName()) 
						&& p.getProductColor().equals(pfd.getProductColor()) 
						&& p.getProductSize().equals(pfd.getProductSize())) {					
					productList.remove(p);					
					break;
				}				
			}			
		}
		
		//某項商品數量增加一個
		if(addOne != null) {			
			CartProduct ao=gson.fromJson(addOne,CartProduct.class);
			productList=(List<CartProduct>)session.getAttribute("productList");
			for(CartProduct p:productList){
				if(p.getProductName().equals(ao.getProductName()) 
						&& p.getProductColor().equals(ao.getProductColor()) 
						&& p.getProductSize().equals(ao.getProductSize())) {					
					p.setProductQuantity(p.getProductQuantity()+1);
					break;
				}				
			}			
		}
		
		//某項商品數量減少一個
		if(minusOne != null) {
			CartProduct mo=gson.fromJson(minusOne,CartProduct.class);
			productList=(List<CartProduct>)session.getAttribute("productList");
			for(CartProduct p:productList){
				if(p.getProductName().equals(mo.getProductName()) 
						&& p.getProductColor().equals(mo.getProductColor()) 
						&& p.getProductSize().equals(mo.getProductSize())) {					
					p.setProductQuantity(p.getProductQuantity()-1);
					if(p.getProductQuantity()==0) 	productList.remove(p);
					break;
				}				
			}			
		}
		//計算總價
		totalPrice(request);
		
		
		
		response.sendRedirect("CartView.jsp");
		//request.getRequestDispatcher("CartView.jsp").forward(request, response);			
	}
    
    public void totalPrice(HttpServletRequest request) {
    	Integer totalPrice=0;
    	List<CartProduct> productList=(List<CartProduct>)request.getSession().getAttribute("productList");
    	if(productList !=null) {
    		for(CartProduct p:productList) {
    			totalPrice=totalPrice+p.getSum().intValue();
    		}
    	}		
    	request.getSession().setAttribute("totalPrice", totalPrice);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
