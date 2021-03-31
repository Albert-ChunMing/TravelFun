package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;

public class MemberDao {
	  public static Connection getConnection(){  
	        Connection con=null;  
	        try{  
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	 con =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST","root","1234");
	        }catch(Exception e){System.out.println(e);}  
	        return con;  
	    }  
	  public static int update(Member m){  
	        int status=0;  
	        try{  
	            Connection con=MemberDao.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "update customers set name=?,password=?,gender=?,email=?,birthday=?,phone=?,address=? where id=?");  
	            ps.setString(1,m.getName());  
	            ps.setString(2,m.getPassword());  
	            ps.setString(3,m.getGender());  
	            ps.setString(4,m.getEmail());  
	            ps.setString(5,m.getBirthday()); 
	            ps.setString(6,m.getPhone());  
	            ps.setString(7,m.getAddress());  
	            ps.setString(8,m.getId());  
	              
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return status;  
	    }  
	  
	  public static Member getCustomerById(String id){  
		  Member m=new Member();  
	          
	        try{  
	            Connection con=MemberDao.getConnection();  
	            PreparedStatement ps=con.prepareStatement("select * from customers where id=?");  
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
	  public static boolean queryUser(String id) throws SQLException
	    {
	        Connection conn=MemberDao.getConnection();  
	        PreparedStatement ps=conn.prepareStatement("select * from customers where id=?");  	      
	        boolean m=false;
	        try {
	          
	            ps.setString(1,id);
	            ResultSet rs=ps.executeQuery();
	            m=rs.next();
	        }catch(Exception ex){ex.printStackTrace();}  
	      return m;      
	    }
}
