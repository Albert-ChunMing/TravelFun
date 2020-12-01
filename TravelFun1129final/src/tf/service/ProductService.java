package tf.service;

import java.util.List;

import tf.entity.Product;
import tf.entity.VGBException;

public class ProductService {
	
ProductsDAO dao = new ProductsDAO();//delegation model
	  
	
	public List<Product> searchAllProducts() throws VGBException{
		return dao.selectAllProduct();
	}
		   
	public List<Product> searchProductsByName(String name) throws VGBException{
		return dao.selectProductsByName(name);
	}

	public List<Product> getProductById(String id) throws VGBException{
		return dao.selectProductById(id);
	}

	public Product getProductByIdp(String id) throws VGBException{
		return dao.selectProductByIdp(id);
	}
	
	
}
	
	  

