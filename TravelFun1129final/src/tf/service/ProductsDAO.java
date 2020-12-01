package tf.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import tf.entity.Product;
import tf.entity.VGBException;



class ProductsDAO {

	
	private static final String SELECT_ALL_PRODUCTS="SELECT id, name, unit_price, description, class_name, discount, category, brand, color_name, photo_main_url FROM products LEFT JOIN product_colors ON id=product_id";
	
	public List<Product> selectAllProduct() throws VGBException {
		List<Product> list = new ArrayList<>();
		try( Connection connection = RDBConnection.getConnection();//1,2取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_PRODUCTS);//3.準備指令
				ResultSet rs = pstmt.executeQuery(); //4.執行指令
				){
			     //5.處理rs
			 while (rs.next()) {
				Product p=new Product();
			    p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setUnitPrice(rs.getDouble("unit_price"));
				p.setCategory(rs.getString("category"));
				p.setPhoto_main_url(rs.getString("Photo_main_url"));
				p.setDiscount(rs.getInt("discount"));
				p.setColorName(rs.getString("color_name"));
				
				list.add(p);
			 }
		}catch(SQLException e) {
			throw new VGBException("查詢全部產品失敗",e);
		}
		
		return list;
	}

    private static final String SELECT_PRODUCTS_BY_NAME ="SELECT id, name, unit_price, description, class_name, discount, category, brand FROM tf.products ";
	public List<Product> selectProductsByName(String name) throws VGBException {
		List<Product> list = new ArrayList<>();
		try( Connection connection = RDBConnection.getConnection();//1,2取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCTS_BY_NAME);//3.準備指令
				
			){
			//3.1傳入?的值
			pstmt.setString(1, '%'+name+'%');
			try(
			ResultSet rs = pstmt.executeQuery(); //4.執行指令
			  ){ 
					//5.處理rs
			 while (rs.next()) {
				Product p = new Product();
				
			    
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setUnitPrice(rs.getDouble("unit_price"));
				
				p.setCategory(rs.getString("category"));
				
				list.add(p);
			    }
			}
		}catch(SQLException e) {
			throw new VGBException("查詢全部產品失敗",e);
		}
		 return list;
	    }
	
	private static final String SELECT_PRODUCT_BY_ID = "SELECT products.id,products.name,products.unit_price,"
			+" products.description,products.discount,products.brand,product_colors.color_name,"
			+ " product_colors.photo_main_url,product_colors.photo_url1,product_colors.photo_url2,"
			+ " product_colors.photo_url3,product_colors.photo_url4,product_size.s_name,product_size.s_number,"
			+ " product_size.s_stock FROM products"
			+ " JOIN product_colors ON products.id=product_colors.product_id"
			+ " JOIN product_size ON product_colors.color_name=product_size.color_name"
			+ " WHERE products.id=?";
	
	public Product selectProductByIdp(String id) throws VGBException {
		Product p = new Product();
		Connection connection = RDBConnection.getConnection(); // 1,2 取得連線		
		try {
			PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				p.setName(rs.getString("name"));
				p.setSizeNumber(rs.getString("s_number"));
				p.setBrand(rs.getString("brand"));
				p.setUnitPrice(rs.getDouble("unit_price"));
				p.setDiscount(rs.getInt("discount"));
				p.setDescription(rs.getString("description"));
			
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return p;		 
		
	}
	
	
public List<Product> selectProductById(String id) throws VGBException {
		
		List<Product> productcolorlist=new ArrayList();
		Connection connection = RDBConnection.getConnection(); // 1,2 取得連線		
		try {
			PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Product p = new Product();				
				p.setColorName(rs.getString("color_name"));
				p.setPhoto_main_url(rs.getString("Photo_main_url"));
				p.setPhotoUrl1(rs.getString("photo_url1"));
				p.setPhotoUrl2(rs.getString("photo_url2"));
				p.setPhotoUrl3(rs.getString("photo_url3"));
				p.setPhotoUrl4(rs.getString("photo_url4"));
				p.setSizeName(rs.getString("s_name"));
				
				productcolorlist.add(p);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return productcolorlist;		 
		
	} 
	
	
}