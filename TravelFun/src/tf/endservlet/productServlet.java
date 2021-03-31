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

import tf.endmodel.endProduct;


/**
 * Servlet implementation class productServlet
 */
@WebServlet("/productServlet")
public class productServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productServlet() {
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
		String unit_pricee=request.getParameter("unit_price");
		String description=request.getParameter("description");
		String class_name=request.getParameter("class_name");
		String discountt=request.getParameter("discount");
		String category=request.getParameter("category");
		String brand=request.getParameter("brand");
		String prosupplierr=request.getParameter("prosupplier");
		String methed=request.getParameter("methed");
		int id=Integer.parseInt(idd);
		Double unit_price=0.0;
		Integer discount=0;
		Integer prosupplier=0;
		if(!unit_pricee.equals("")) unit_price=Double.parseDouble(unit_pricee);
		if(!discountt.equals("")) discount=Integer.parseInt(discountt);
		if(!prosupplierr.equals("")) prosupplier=Integer.parseInt(prosupplierr);
		
		endProduct s=new endProduct(id,name,unit_price,description,class_name,discount,category,brand,prosupplier);
		
		if(methed.equals("add"))
		add(s);
		if(methed.equals("update"))
		update(s);	
		if(methed.equals("delete"))
		delete(s);	
		}
			
		List<endProduct> data;
		try {
			data = query();
			request.setAttribute("product", data);
			request.getRequestDispatcher("productshow.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void delete(endProduct p)
	{
		Connection con=null;
		PreparedStatement st=null;
		String sql="delete from tf.products where id=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setInt(1,p.getId());
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private endProduct queryid(int id) {
		// TODO Auto-generated method stub

		Connection con=null;
		
        String sql="select * from tf.products where id=?";
        endProduct p=null;
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
	   
			PreparedStatement ps;
	
			ps = con.prepareStatement(sql);
			  ps.setInt(1,id);
		        ResultSet rs=ps.executeQuery();
		        rs.next();
		        p=new endProduct(rs.getString("name"),rs.getDouble("unit_price"),rs.getString("description"),rs.getString("class_name"),rs.getInt("discount"),rs.getString("category"),rs.getString("brand"),rs.getInt("supplier_id"));
		        p.setId(rs.getInt("id"));
		        return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
     return p;
     	
	}
	
	
	void update(endProduct o)
	{
		
		Connection con=null;
		PreparedStatement st=null;
		endProduct p=(endProduct )o;
		endProduct p2=(endProduct)queryid(o.getId());
		
		if(!p.getName().equals("")) p2.setName(p.getName());//規則已確定好後到這更新
        if(p.getUnit_price()!=0.0) p2.setUnit_price(p.getUnit_price());
        if(!p.getDescription().equals("")) p2.setDescription(p.getDescription());
        if(!p.getClass_name().equals("")) p2.setClass_name(p.getClass_name());
        if(p.getDiscount()!=0) p2.setDiscount(p.getDiscount());
        if(!p.getCategory().equals("")) p2.setCategory(p.getCategory());
        if(!p.getBrand().equals("")) p2.setBrand(p.getBrand());
        if(p.getProsupplier()!=0) p2.setProsupplier(p.getProsupplier());
		String sql="update tf.products set name=?,unit_price=?,description=?,class_name=?,discount=?,category=?,brand=?,supplier_id=? where id=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			
			st.setString(1,p2.getName());
			st.setDouble(2,p2.getUnit_price());
			st.setString(3,p2.getDescription());
			st.setString(4,p2.getClass_name());
			st.setInt(5,p2.getDiscount());
			st.setString(6,p2.getCategory());
			st.setString(7,p2.getBrand());
			st.setInt(8,p2.getProsupplier());
			st.setInt(9,p2.getId());
			
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	void add(endProduct p)
	{
		
		Connection con=null;
		PreparedStatement st=null;
		String sql="insert into tf.products(id,name,unit_price,description,class_name,discount,category,brand,supplier_id) values(?,?,?,?,?,?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setInt(1,p.getId());
			st.setString(2,p.getName());
			st.setDouble(3,p.getUnit_price());
			st.setString(4,p.getDescription());
			st.setString(5,p.getClass_name());
			st.setInt(6,p.getDiscount());
			st.setString(7,p.getCategory());
			st.setString(8,p.getBrand());
			st.setInt(9,p.getProsupplier());
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	List<endProduct> query() throws Exception
	{
		List<endProduct> list=new ArrayList<>();
		Connection con=null;
		Statement st=null;
		String sql="select*from tf.products ";
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
		st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next())
		{
			int id=rs.getInt("id");
			String name=rs.getString("name");
			double unit_price=rs.getDouble("unit_price");
			String description=rs.getString("description");
			String class_name=rs.getString("class_name");
			int discount=rs.getInt("discount");
			String category=rs.getString("category");
			String brand=rs.getString("brand");
			int prosupplier=rs.getInt("supplier_id");
			endProduct p=new endProduct(id,name,unit_price,description,class_name,discount,category,brand,prosupplier);
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
