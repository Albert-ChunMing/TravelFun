package tf.entity;

public class Outlet extends Product {


	
	public Outlet() {
		super();
		
	}

	public Outlet(int id, String name, double unitPrice, int stock) {
		super(id, name, unitPrice, stock);
		
	}
    public Outlet(int id, String name, double Price) {
		super(id, name, Price);
		
	}

	
    private int discount; //required
	
	public int getDiscount() {
		
		return discount;
	}
	
	public void setDiscount(int discount) {
		this.discount=discount;
		}
	
	public String getDiscountString() {
		int discount = 100-this.discount;
		if(discount%10==0) {
			discount = discount/10;
		}
		return discount + "折";
	}

	
	@Override
	public double getUnitPrice() { //查詢售價
		return super.getUnitPrice()*(100-discount)/100;
	}
	
	public double getListPrice() { //查詢定價
	
		return super.getUnitPrice();
		
	}

	@Override
	public String toString() {
		return super.toString()
				+"\n[discount=" + getDiscountString() + "]"
				+"price="+ getUnitPrice();
		}
	
	

}
