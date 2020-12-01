package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
	//取得資料庫連線
	public static Connection getConn() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST", "root", "1234" );			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}	
	//產生物件(物件內只有 id name price)
	public static CartProduct getProductByName(String name) {
		CartProduct p=new CartProduct();
		String sql="select * from products where name=?";
		Connection conn=ProductDAO.getConn();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				p.setProductId(rs.getInt("id"));
				p.setProductName(rs.getString("name"));
				p.setProductPrice(rs.getDouble("unit_price")*0.9);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return p;
	}
	//設定物件的相片url
	public static void setPhotoURL(String name,String color,CartProduct p) {
		String sql="SELECT * FROM tf.products join tf.product_colors "
				+ "on products.id=product_colors.product_id where name=? and color_name=?";
		Connection conn=ProductDAO.getConn();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, color);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				p.setUrl(rs.getString("photo_main_url"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	//設定物件的貨號itemNumber
		public static void setProductItemNumber(String name,String color,String size,CartProduct p) {
			String sql="SELECT * FROM tf.products join tf.product_colors on products.id=product_colors.product_id "
					+ "join tf.product_size on product_colors.color_name=product_size.color_name "
					+ "where name=? and product_colors.color_name=? and s_name=?";
			Connection conn=ProductDAO.getConn();
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, color);
				ps.setString(3, size);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					p.setItemNumber(rs.getString("s_number"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	
	public static void main(String[] args) {
        //以下為功能測試
		System.out.println(ProductDAO.getProductByName("舒適素色-美麗諾羊毛頭巾").getProductPrice());
		CartProduct p=ProductDAO.getProductByName("舒適素色-美麗諾羊毛頭巾");
		p.setProductColor("珍珠粉");
		p.setProductSize("單一尺寸");
		ProductDAO.setProductItemNumber(p.getProductName(), p.getProductColor(), p.getProductSize(), p);
		System.out.println(p.getItemNumber());

	}

}
