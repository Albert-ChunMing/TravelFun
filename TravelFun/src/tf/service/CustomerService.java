package tf.service;

import tf.entity.Customer;
import tf.entity.VGBException;

public class CustomerService {
	CustomersDAO dao = new CustomersDAO();
	public Customer login(String id, String pwd) throws VGBException {
		  
		Customer c = dao.selectCustomerById(id);
	 
	 
	  if(c!=null) {
		  if(c.getPassword().equals(pwd) && c.getId().equals(id)) {
			  
			  
			  return c;
			  
		  }
	  }
	  
	  throw new VGBException("登入失敗，帳號或密碼不正確");
  }

      public void register(Customer c)throws VGBException{
    	   dao.insert(c);
      }

	public void update(Customer c) throws VGBException {
		dao.updata(c);
	}
}