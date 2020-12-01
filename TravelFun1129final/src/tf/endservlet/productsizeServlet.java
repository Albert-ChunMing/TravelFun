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

import tf.endmodel.endProductSize;


/**
 * Servlet implementation class productsizeServlet
 */
@WebServlet("/productsizeServlet")
public class productsizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productsizeServlet() {
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
			String sizecolorname=request.getParameter("sizecolorname");
			String sizename=request.getParameter("sizename");
			String sizenumber=request.getParameter("sizenumber");
			String sizestockk=request.getParameter("sizestock");
			String methed=request.getParameter("methed");
			
			Integer sizestock=0;
			if(!sizestockk.equals("")) sizestock=Integer.parseInt(sizestockk);
			endProductSize p=new endProductSize(sizecolorname,sizename,sizenumber,sizestock);
			
			if(methed.equals("add"))
				add(p);
				if(methed.equals("update"))
				update(p);	
				if(methed.equals("delete"))
				delete(p);	
		}
		List<endProductSize> data;
		try {
			data = query();
			request.setAttribute("productsize", data);
			request.getRequestDispatcher("productsizeshow.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void delete(endProductSize p)
	{
		Connection con=null;
		PreparedStatement st=null;
		String sql="delete from tf.product_size where s_number=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setString(1,p.getS_number());
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private endProductSize queryid(String number) {
		// TODO Auto-generated method stub

		Connection con=null;
		
        String sql="select * from tf.product_size where s_number=?";
        endProductSize p=null;
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
	   
			PreparedStatement ps;
	
			ps = con.prepareStatement(sql);
			  ps.setString(1,number);
		        ResultSet rs=ps.executeQuery();
		        rs.next();
		        p=new endProductSize(rs.getString("color_name"),rs.getString("s_name"),rs.getInt("s_stock"));
		        p.setS_number(rs.getString("s_number"));
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
	
	void update(endProductSize o)
	{
		
		Connection con=null;
		PreparedStatement st=null;
		endProductSize p=(endProductSize)o;
		endProductSize p2=(endProductSize)queryid(o.getS_number());
		
		if(!p.getColor_name().equals("")) p2.setColor_name(p.getColor_name());//規則已確定好後到這更新
        if(!p.getS_name().equals("")) p2.setS_name(p.getS_name());
        if(p.getS_stock()!=0) p2.setS_stock(p.getS_stock());
       
		String sql="update tf.product_size set color_name=?,s_name=?,s_stock=? where s_number=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			
			st.setString(1,p2.getColor_name());
			st.setString(2,p2.getS_name());
			st.setInt(3,p2.getS_stock());
			st.setString(4,p2.getS_number());
			
			
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	void add(endProductSize p)
	{
		
		Connection con=null;
		PreparedStatement st=null;
		String sql="insert into product_size(color_name,s_name,s_number,s_stock) values(?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setString(1,p.getColor_name());
			st.setString(2,p.getS_name());
			st.setString(3,p.getS_number());
			st.setInt(4,p.getS_stock());
			
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	List<endProductSize> query() throws Exception
	{
		List<endProductSize> list=new ArrayList<>();
		Connection con=null;
		Statement st=null;
		String sql="select*from tf.Product_size ";
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
		st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next())
		{
			
			String name=rs.getString("color_name");
			String sname=rs.getString("s_name");
			String snumber=rs.getString("s_number");
			int stock=rs.getInt("s_stock");
			endProductSize p=new endProductSize(name,sname,snumber,stock);
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
