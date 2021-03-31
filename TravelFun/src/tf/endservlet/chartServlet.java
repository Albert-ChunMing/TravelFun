package tf.endservlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tf.endmodel.endchart;



/**
 * Servlet implementation class charServlet
 */
@WebServlet("/chartServlet")
public class chartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<endchart> data;
		try {
			data = query();
			request.setAttribute("message", data);
			request.getRequestDispatcher("chartshow.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	List<endchart> query() throws Exception
	{
		List<endchart> list=new ArrayList<>();
		Connection con=null;
		Statement st=null;
		String sql="call tf.total()";
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
		st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next())
		{
			int sum=rs.getInt("sum");
			int avg=rs.getInt("avg");
			endchart c=new endchart(sum,avg);
			list.add(c);
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
