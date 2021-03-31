package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Order;
import model.Orderdetail;
/*
 * 處理Order Orderdetail資料的寫入
 * 需先使用addOrder()寫入order(table)後
 * 在使用queryLastId()取得order中最後一筆id值
 * 以queryLastId()取得的id值將session 中的產品資料作為orderdetail的table寫入
 * 使用上必須注意使用順序
 * 因table order對orderdetail為一對多關聯式表單
 */
public class OrderAndDetailDAO {

			public static void addOrder(Order order) throws SQLException, ClassNotFoundException {
				Connection conn=null;
				PreparedStatement ps=null;
				//用於新增order表單語法
				String insertOrder="INSERT INTO tf.order(customerid,ordertime,recipient,phone,address,statuscode,totalsales) values(?,?,?,?,?,?,?)";
				//用於新增orderdetail語法
				String insertDetail="insert into orderdetail(orderid,productname,size,color,itemnumber,quantity,price,sales) values(?,?,?,?,?,?,?,?)";
				//建立Connection
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST", "root", "1234" );
				//執行order新增寫入
				ps=conn.prepareStatement(insertOrder);
				ps.setString(1,order.getCustomerId());
				ps.setString(2,order.getOrderTime());
				ps.setString(3,order.getRecipient());
				ps.setString(4,order.getPhone());
				ps.setString(5,order.getAddress());
				ps.setString(6,order.getStatusCode());
				ps.setInt(7,order.getTotalSales());
				ps.execute();
				
			}
			//查詢order的最後一筆orderid值
			public static Integer queryLastId() {
				Connection conn=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				Integer lastid=null;
				String queryLastId="select * from tf.order order by Orderid desc limit 1;";
				//建立Connection
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST", "root", "1234" );
					ps=conn.prepareStatement(queryLastId);
					rs=ps.executeQuery();
					if(rs.next()) {
						return lastid=rs.getInt("Orderid");
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return lastid;
				
			}
			//利用orderid為FK寫入orderdetail
			public static void addOrderDetail(Orderdetail od,Integer lastid) throws Exception {
				Connection conn=null;
				PreparedStatement ps=null;
				String insertDetail="insert into tf.orderdetail(Orderid,ProductName,Size,Color,ItemNumber,Quantity,Price,Sales) values(?,?,?,?,?,?,?,?)";
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST", "root", "1234" );
				//執行orderdetail新增寫入
				ps=conn.prepareStatement(insertDetail);
				ps.setInt(1,lastid);
				ps.setString(2,od.getProductName());
				ps.setString(3,od.getSize());
				ps.setString(4,od.getColor());
				ps.setString(5,od.getItemNumber());
				ps.setInt(6,od.getQuantity());
				ps.setInt(7,od.getPrice());
				ps.setInt(8,od.getSales());
				ps.execute();
			}
}
