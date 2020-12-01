package memberServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.Member;
import model.MemberDao;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/register.do")
public class InsertServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
    public void InsertServlet() {
    
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
		
	}
	public boolean InsertMember(String id,String name,String password,String gender,String email,String birthday,String phone,String address)
		    throws SQLException {
		
		Connection con=null;
	    PreparedStatement  insert= null;
	    String insertStatement =
	            "insert into customers(id,name,password,gender,email,birthday,phone,address)" +
	            "values (?,?,?,?,?,?,?,?)";

	        try {
	             Class.forName("com.mysql.cj.jdbc.Driver");
	             con =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST","root","1234");
	          
	            con.setAutoCommit(false);   //=start transaction(sql);
	            insert = (PreparedStatement) con.prepareStatement(insertStatement);

	            //for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) { }
	               insert.setString(1,id);
	               insert.setString(2,name); 
	               insert.setString(3,password); 
	               insert.setString(4,gender); 
	               insert.setString(5,email); 
	               insert.setString(6,birthday); 
	               insert.setString(7,phone); 
	               insert.setString(8,address); 
	              
	               int rs=insert.executeUpdate();
	                con.commit();
	                if(rs>0)
	                {return true;}
	                else
	                {return false;}
	                
	            
	        } catch (Exception e ) {
	            System.out.println(e.getMessage());
	            if (con != null) {
	                try {
	                	 System.err.print("Transaction is being rolled back");
	                     con.rollback();
	                 } catch(SQLException ex) {
	                     System.out.println(ex.getMessage());
	                 }
	             }
	         } finally {
	             if (insert != null) {
	                 insert.close();
	             }
	             if (insert != null) {
	                 insert.close();
	             }
	             con.setAutoCommit(true);
	         }
			return false;
	        
	     }  
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	 	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          
        	String id=request.getParameter("id");
            String name=request.getParameter("name");
            String password=request.getParameter("password");         
            String gender=request.getParameter("gender");
            String email=request.getParameter("email");    
            String birthday=request.getParameter("birthday"); 
            String phone=request.getParameter("phone"); 
            String address=request.getParameter("address"); 
            
            try {
            	if(new MemberDao().queryUser(id)==true)
                {
            		out.println("這個帳號已經被使用，三秒後返回註冊頁面");
            		response.setHeader("refresh","3;URL=register.jsp");
                    
                }
            	else
            	{
            		boolean flag=InsertMember(id,name,password,gender,email,birthday,phone,address);
            		if(flag)
               	{
                	
                	//request.getRequestDispatcher("login.jsp").forward(request, response);
                	response.setHeader("refresh","3;URL=login.jsp");
                	out.println("新增成功，頁面將三秒後跳轉至登入頁面");
          		 
                }else
                {
                	out.println("新增失敗，有問題請洽客服人員");
                }
            }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } finally {            
            out.close();
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
