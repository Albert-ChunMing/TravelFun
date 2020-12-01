package tf.endservlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tf.endmodel.endProductColors;


/**
 * Servlet implementation class productcolorServlet
 */
@WebServlet("/productcolorServlet")
public class productcolorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productcolorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
		if(request.getParameter("methed") != null) {
			String idd=request.getParameter("id");
			String name=request.getParameter("name");
			String url=request.getParameter("url");
			String url1=request.getParameter("url1");
			String url2=request.getParameter("url2");
			String url3=request.getParameter("url3");
			String url4=request.getParameter("url4");
			String item_number=request.getParameter("item_number");
			
			String methed=request.getParameter("methed");
			int id=Integer.parseInt(idd);
			
			endProductColors s=new endProductColors(id,name,url,url1,url2,url3,url4,item_number);
			
			if(methed.equals("add"))
			add(s);
			if(methed.equals("update"))
			update(s);	
			if(methed.equals("delete"))
			delete(s);	
			}
				
			List<endProductColors> data;
			try {
				data = query();
				request.setAttribute("productcolor", data);
				request.getRequestDispatcher("productcolorshow.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	void delete(endProductColors p)
	{
		Connection con=null;
		PreparedStatement st=null;
		String sql="delete from tf.Product_Colors where product_id=? and color_name=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setInt(1,p.getProduct_id());
			st.setString(2,p.getColor_name());
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	void update(endProductColors p)
	{
		
		Connection con=null;
		PreparedStatement st=null;
		String sql="update tf.Product_Colors set photo_main_url=?,photo_url1=?,photo_url2=?,photo_url3=?,photo_url4=?,item_number=? where product_id=? and color_name=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setString(1,p.getPhoto_main_url());
			st.setString(2,p.getPhoto_url1());
			st.setString(3,p.getPhoto_url2());
			st.setString(4,p.getPhoto_url3());
			st.setString(5,p.getPhoto_url4());
			st.setString(6,p.getItem_number());
			st.setInt(7,p.getProduct_id());
			st.setString(8,p.getColor_name());
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	void add(endProductColors p)
	{
		
		Connection con=null;
		PreparedStatement st=null;
		String sql="insert into tf.Product_Colors(product_id,color_name,photo_main_url,photo_url1,photo_url2,photo_url3,photo_url4,item_number) values(?,?,?,?,?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setInt(1,p.getProduct_id());
			st.setString(2,p.getColor_name());
			st.setString(3,p.getPhoto_main_url());
			st.setString(4,p.getPhoto_url1());
			st.setString(5,p.getPhoto_url2());
			st.setString(6,p.getPhoto_url3());
			st.setString(7,p.getPhoto_url4());
			st.setString(8,p.getItem_number());
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
		List<endProductColors> query() throws Exception
		{
			List<endProductColors> list=new ArrayList<>();
			Connection con=null;
			Statement st=null;
			String sql="select*from tf.Product_Colors ";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				int id=rs.getInt("product_id");
				String name=rs.getString("color_name");
				String url=rs.getString("photo_main_url");
				String url1=rs.getString("photo_url1");
				String url2=rs.getString("photo_url2");
				String url3=rs.getString("photo_url3");
				String url4=rs.getString("photo_url4");
				String item_number=rs.getString("item_number");
			
				endProductColors p=new endProductColors(id,name,url,url1,url2,url3,url4,item_number);
				list.add(p);
			}
			return list;
		}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
