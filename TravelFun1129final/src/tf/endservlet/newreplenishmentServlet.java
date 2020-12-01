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

import tf.endmodel.endreplequery;


/**
 * Servlet implementation class newreplenishmentServlet
 */
@WebServlet("/newreplenishmentServlet")
public class newreplenishmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newreplenishmentServlet() {
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
		if(request.getParameter("number")!=null)
		{
			String color=request.getParameter("color");
			String number=request.getParameter("number");
			String stockk=request.getParameter("stock");
			int stock=Integer.parseInt(stockk);
			endreplequery p=new endreplequery(color,number,stock);
			update(p);
			
		}
		List<endreplequery> data;
		try {
			data = query();
			request.setAttribute("Object", data);
			request.getRequestDispatcher("newrepleshow.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	void update(endreplequery p)
	{
		
		Connection con=null;
		PreparedStatement st=null;
		
		String sql="update tf.product_size set s_stock=? where s_number=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setInt(1,p.getStock());
			st.setString(2,p.getNamenumber());
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	List<endreplequery> query() 
	{
		List<endreplequery> list=new ArrayList<>();
		Connection con=null;
		Statement st=null;
		String sql="SELECT pr.id,pr.name,pc.color_name,ps.s_number,ps.s_stock FROM products as pr left join product_colors as pc on pr.id=pc.product_id left join product_size as ps on pc.color_name=ps.color_name";
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String color_name=rs.getString("color_name");
				String s_number=rs.getString("s_number");
				int s_stock=rs.getInt("s_stock");
				
				endreplequery p=new endreplequery(id,name,color_name,s_number,s_stock);
				list.add(p);
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
