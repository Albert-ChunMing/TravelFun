package memberServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



import model.Member;
import model.MemberDao;



/**
 * Servlet implementation class UpdatetServlet3
 */
@WebServlet("/UpdatetServlet3")
public class UpdatetServlet3 extends HttpServlet {
 private static final long serialVersionUID = 1L;
private static final Object METHOD_GET = null;
private static final Object METHOD_POST = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatetServlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	 		String id=request.getParameter("id");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			Member m=new Member();
	        m=getCustomerById(id) ;
	        boolean flag= m != null;  
	        if(flag)
	        {
	        	Gson   gson=new Gson();	
	    		String j=gson.toJson(m);
	    		response.getWriter().append(j);
	        }          
	}

 public boolean UpdateCustomer(String id,String name,String password,String gender,String email,String birthday,String phone,String address)
      throws SQLException {
      Connection con=null;
      PreparedStatement  insert= null;
      String insertStatement =
        "update customers set name=?,password=?,gender=?,email=?,birthday=?,phone=?,address=? where id=?";

          try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST","root","1234");
            
               con.setAutoCommit(false);   //=start transaction(sql);
               insert = con.prepareStatement(insertStatement);

                 insert.setString(1, name); 
                 insert.setString(2,password);           
                 insert.setString(3,gender);
                 insert.setString(4,email);
                 insert.setString(5,birthday);
                 insert.setString(6,phone);
                 insert.setString(7,address);
                 insert.setString(8,id);
                 int rs=insert.executeUpdate();
                  con.commit();
                  if(rs>0){
                	  return true;
                  }
                  else{
                	  return false;
                  }                  
              
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
               con.setAutoCommit(true);
           }
          return false;
          
       }   

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {

			    String method = request.getMethod();

			    if (method.equals(METHOD_GET)) {
			    	doGet(request, response);

			    } else if (method.equals(METHOD_POST)) {
			        doPost(request, response);

			    } 
	  
  }
  public static Member getCustomerById(String id){  
	    Member m=new Member();  
	           
	         try{  
	             Connection con=(Connection) MemberDao.getConnection();  
	             PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from customers where id=?");  
	             ps.setString(1,id);  
	             ResultSet rs=ps.executeQuery();  
	             if(rs.next()){  
	              m.setId(rs.getString("id")); 
	                 m.setName(rs.getString("name"));  
	                 m.setPassword(rs.getString("password"));  
	                 m.setGender(rs.getString("gender"));
	                 m.setEmail(rs.getString("email"));  
	                 m.setBirthday(rs.getString("birthday"));
	                 m.setPhone(rs.getString("phone"));
	                 m.setAddress(rs.getString("address"));
	                
	             }  
	             con.close();  
	         }catch(Exception ex){ex.printStackTrace();}  
	           
	         return m;  
	     }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	// TODO Auto-generated method stub
	    request.setCharacterEncoding("UTF-8");

            String id=request.getParameter("id");
            String name=request.getParameter("name");
            String password=request.getParameter("password2");         
            String gender=request.getParameter("gender");
            String email=request.getParameter("email");    
            String birthday=request.getParameter("birthday"); 
            String phone=request.getParameter("phone"); 
            String address=request.getParameter("address"); 
            try {
            	boolean flag=UpdateCustomer(id,name,password,gender,email,birthday,phone,address);
                if(flag) {
                     request.setAttribute("updateStatus", "修改成功");
                }else {
                	request.setAttribute("updateStatus", "修改失敗");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                request.setAttribute("updateStatus", "修改失敗"+ex.getMessage());                
            }finally {
            	request.getRequestDispatcher("/updatemember.jsp").forward(request, response);
            }
 	}

}         

