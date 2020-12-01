package tf.service;
import java.sql.*;

import tf.entity.VGBException;

class RDBConnection {

	private static final String driver="com.mysql.cj.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/tf?serverTimezone=CST";//serverTimezone=CST mysql8.0後資料庫連法
	private static final String user="root";
	private static final String password = "1234";

	
	
	
	
	
	public static Connection getConnection() throws VGBException {
		 
		try {
			Class.forName(driver); //1.載入JDBC Driver
		
			try {
				Connection connection = DriverManager.getConnection(url, user, password);//2.建立連線
	            return connection;		
			} catch (SQLException e) {
				
				throw new VGBException("建立連線失敗",e);
			}
			
			
		} catch (ClassNotFoundException e) {
			
            throw new VGBException("無法載入JDBC Driver",e);
		}
	      
	 }
	public static void main(String[] args) {
		
		try(
				Connection connection = RDBConnection.getConnection(); //1,2.取得連線
			) {
			
				System.out.println(connection.getCatalog());
			} catch (SQLException e) {
				
				
			System.out.println(e);
		} catch (VGBException e1) {
			
		 System.out.println(e1);
		}
		System.out.println("結束");
	}
}
