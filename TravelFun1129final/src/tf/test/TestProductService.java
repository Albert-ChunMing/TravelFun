package tf.test;

import java.util.List;

import tf.entity.Product;
import tf.entity.VGBException;
import tf.service.ProductService;

public class TestProductService {

	public static void main(String[] args) {
		ProductService service= new ProductService();
		try {
			List<Product> list = service.searchAllProducts();
			System.out.println(list);
			System.out.println(list !=null? list.size():0);
			System.out.println("-------------------------------------");
			 
		    }catch (VGBException e) {
				e.printStackTrace();
			}

	}

}
