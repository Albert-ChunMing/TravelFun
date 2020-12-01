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

import tf.endmodel.endSupplier;


/**
 * Servlet implementation class supplierServlet
 */
@WebServlet("/supplierServlet")
public class supplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public supplierServlet() {
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
			String companyname=request.getParameter("companyname");
			String address=request.getParameter("address");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			String salesName=request.getParameter("salesName");
			String salesPhone=request.getParameter("salesPhone");
			String salesEmail=request.getParameter("salesEmail");
			String methed=request.getParameter("methed");
			int id=Integer.parseInt(idd);
			
			endSupplier s=new endSupplier(id,companyname,address,email,phone,salesName,salesPhone,salesEmail);
			
			if(methed.equals("add"))
			add(s);
			if(methed.equals("update"))
			update(s);	
			if(methed.equals("delete"))
			delete(s);	
			}
				
			List<endSupplier> data;
			try {
				data = query();
				request.setAttribute("supplier", data);
				request.getRequestDispatcher("suppliershow.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
	
	private endSupplier queryid(int id) {
		// TODO Auto-generated method stub

		Connection con=null;
		
        String sql="select * from tf.suppliers where id=?";
        endSupplier p=null;
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
	   
			PreparedStatement ps;
	
			ps = con.prepareStatement(sql);
			  ps.setInt(1,id);
		        ResultSet rs=ps.executeQuery();
		        rs.next();
		        p=new endSupplier(rs.getString("companyname"),rs.getString("address"),rs.getString("email"),rs.getString("phone"),rs.getString("salesName"),rs.getString("salesPhone"),rs.getString("salesEmail"));
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

	void delete(endSupplier p)
	{
		Connection con=null;
		PreparedStatement st=null;
		String sql="delete from tf.Suppliers where id=?";
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
	
	
	void update(endSupplier o)
	{	
		Connection con=null;
		PreparedStatement st=null;
		endSupplier p=(endSupplier )o;
		endSupplier p2=(endSupplier)queryid(o.getId());
		
		if(!p.getCompanyname().equals("")) p2.setCompanyname(p.getCompanyname());//規則已確定好後到這更新
        if(!p.getAddress().equals("") ) p2.setAddress(p.getAddress());
        if(!p.getEmail().equals("")) p2.setEmail(p.getEmail());;
        if(!p.getPhone().equals("")) p2.setPhone(p.getPhone());
        if(!p.getSalesName().equals("")) p2.setSalesName(p.getSalesName());;
        if(!p.getSalesPhone().equals("")) p2.setSalesPhone(p.getSalesPhone());
        if(!p.getSalesEmail().equals("")) p2.setSalesEmail(p.getSalesEmail());
		String sql="update tf.suppliers set companyname=?,address=?,email=?,phone=?,salesName=?,salesPhone=?,salesEmail=? where id=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			
			st.setString(1,p2.getCompanyname());
			st.setString(2,p2.getAddress());
			st.setString(3,p2.getEmail());
			st.setString(4,p2.getPhone());
			st.setString(5,p2.getSalesName());
			st.setString(6,p2.getSalesPhone());
			st.setString(7,p2.getSalesEmail());
			st.setInt(8,p2.getId());
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}		

	
	void add(endSupplier p)
	{
		Connection con=null;
		PreparedStatement st=null;
		String sql="insert into tf.suppliers(id,companyname,address,email,phone,salesName,salesPhone,salesEmail) values(?,?,?,?,?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setInt(1,p.getId());
			st.setString(2,p.getCompanyname());
			st.setString(3,p.getAddress());
			st.setString(4,p.getEmail());
			st.setString(5,p.getPhone());
			st.setString(6,p.getSalesName());
			st.setString(7,p.getSalesPhone());
			st.setString(8,p.getSalesEmail());
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
			List<endSupplier> query() throws Exception
			{
				List<endSupplier> list=new ArrayList<>();
				Connection con=null;
				Statement st=null;
				String sql="select*from tf.suppliers";
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
				st=con.createStatement();
				ResultSet rs=st.executeQuery(sql);
				while(rs.next())
				{
					int id1=rs.getInt("id");
					String companyname1=rs.getString("companyname");
					String address1=rs.getString("address");
					String email1=rs.getString("email");
					String phone1=rs.getString("phone");
					String salesName1=rs.getString("salesName");
					String salesPhone1=rs.getString("salesPhone");
					String salesEmail1=rs.getString("salesEmail");
					endSupplier p=new endSupplier(id1,companyname1,address1,email1,phone1,salesName1,salesPhone1,salesEmail1);
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
