package tf.service;

import tf.entity.Customer;
import tf.entity.DuplicateKeyException;
import tf.entity.VGBException;


import java.sql.*;


public class CustomersDAO {

	private static final String SELECT_CUSTOMER_BY_ID="SELECT id, name, password, gender, email, birthday, phone, address, discount, class_name "
    		+ "FROM customers WHERE id=?";
	public Customer selectCustomerById(String id) throws VGBException {
		Customer c = null;
		
		try(
		Connection connection = RDBConnection.getConnection();//1,2.取得連線
		
		PreparedStatement pstmt=connection.prepareStatement(SELECT_CUSTOMER_BY_ID);//3.準備指令
		){
			//3.1傳入?的值
			pstmt.setString(1, id);
		
			
			try(
			ResultSet rs = pstmt.executeQuery();//4.執行指令
				){
				//5.讀取rs中的資料
				while(rs.next()) {
					
					
						c = new Customer();
					
					
					c.setId(rs.getString("id"));
					c.setName(rs.getString("name"));
					c.setPassword(rs.getString("password"));
					c.setGender(rs.getString("gender").charAt(0));
					c.setEmail(rs.getString("email"));
					c.setBirthday(rs.getString("birthday"));
					c.setPhone(rs.getString("phone"));
					c.setAddress(rs.getString("address"));
					c.setClassname(rs.getString("class_name"));
				}
			}
			
			
		}catch(SQLException ex) {
			throw new VGBException("查詢客戶失敗",ex);
			
		}
		return c;
	}
     private static final String INSERT_CUSTOMER ="INSERT INTO customers"
     + " (id, name, password, gender, email, birthday, phone, address )"
     + " VALUES(?,?,?,?,?,?,?,?)";
     
     public void insert(Customer c) throws VGBException{
    	 
    	 
 		
 		try (Connection connection = RDBConnection.getConnection();//1,2.取得連線
 	 			PreparedStatement pstmt=connection.prepareStatement(INSERT_CUSTOMER);//3.準備指令
 				){
 			//3.傳入?的值
 			pstmt.setString(1,c.getId());
 			pstmt.setString(2,c.getName());
 			pstmt.setString(3,c.getPassword());
 			pstmt.setString(3,c.getName());
 			pstmt.setString(4,String.valueOf(c.getGender()));
 			pstmt.setString(5,c.getEmail());
 			pstmt.setString(6,String.valueOf(c.getBirthday()));
 		//	pstmt.setString(6,c.getBirthday()==null?null:String.valueOf(c.getBirthday())); 如果生日為非必要欄位，要這樣寫
 			pstmt.setString(7,c.getPhone());
 			pstmt.setString(8,c.getAddress());
 			
 			
 			
 			//4.執行指令
 		   int rows = pstmt.executeUpdate();	
	    	
 		} catch (SQLException e) {
 			
 			if(e instanceof SQLIntegrityConstraintViolationException) {
 				String keyColumn =null;
 				if(e.getMessage().indexOf("email_UNIQUE")>0) {
 					keyColumn ="email";
 				}else if(e.getMessage().indexOf("PRIMARY")>=0) {
 					keyColumn="id";
 				}
 				if(keyColumn!=null) {
 				
 				throw new DuplicateKeyException("新增客戶失敗-"+keyColumn+"已重複註冊("+e.getErrorCode()+")",e, keyColumn);
 				}
 			}
 			    throw new VGBException("新增客戶失敗"+e.getMessage()+"("+e.getErrorCode()+")"+e.getErrorCode(),e);
			    
		}	
	}
          
     private static final String UPDATE_CUSTOMER="UPDATE customers" + 
     		" SET name=?, password=?, email=?, birthday=?, phone=?, address=?, " + 
     		" WHERE id = ?";
		
     
    	     
    	     public void updata(Customer c) throws VGBException{
    	    	 
    	    	 
    	 		
    	 		try (Connection connection = RDBConnection.getConnection();//1,2.取得連線
    	 	 			PreparedStatement pstmt=connection.prepareStatement(UPDATE_CUSTOMER);//3.準備指令
    	 				){
    	 			//3.傳入?的值
    	 			pstmt.setString(9,c.getId());
    	 			pstmt.setString(1,c.getName());
    	 			pstmt.setString(2,c.getPassword());
    	 			pstmt.setString(3,String.valueOf(c.getGender()));
    	 			pstmt.setString(4,c.getEmail());
    	 			pstmt.setString(5,String.valueOf(c.getBirthday()));
    	 		//	pstmt.setString(5,c.getBirthday()==null?null:String.valueOf(c.getBirthday())); 如果生日為非必要欄位，要這樣寫
    	 			pstmt.setString(6,c.getPhone());
    	 			pstmt.setString(7,c.getAddress());
    	 			
    	 			
    	 			
    	 			//4.執行指令
    	 		   int rows = pstmt.executeUpdate();	
    		    	
    	 		} catch (SQLException e) {
    	 			
    	 			if(e instanceof SQLIntegrityConstraintViolationException) {
    	 				String keyColumn =null;
    	 				if(e.getMessage().indexOf("email_UNIQUE")>0) {
    	 					keyColumn ="email";
    	 				}else if(e.getMessage().indexOf("PRIMARY")>=0) {
    	 					keyColumn="id";
    	 				}
    	 				if(keyColumn!=null) {
    	 				
    	 				throw new DuplicateKeyException("修改客戶失敗-"+keyColumn+"已重複("+e.getErrorCode()+")",e, keyColumn);
    	 				}
    	 			}
    	 			    throw new VGBException("修改客戶失敗"+e.getMessage()+"("+e.getErrorCode()+")"+e.getErrorCode(),e);
    				    
    			}	
    		}
		}


