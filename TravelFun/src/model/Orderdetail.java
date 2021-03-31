package model;

public class Orderdetail extends CartProduct{
		String ProductName,Size,Color,ItemNumber;
		Integer OrderId,Quantity,Price,Sales;
	
		public Orderdetail() {
			super();
		}
		public Orderdetail(Integer orderid, CartProduct p) {
			this.OrderId = orderid;
			ProductName =p.getProductName();
			Size = p.getProductSize();
			Color =p.getProductColor();
			ItemNumber = p.getItemNumber();
			Quantity = p.getProductQuantity();
			Price = p.getProductPrice().intValue();
			Sales = p.getSum().intValue();
		}
		public Integer getOrderId() {
			return OrderId;
		}
		public void setOrderId(Integer orderId) {
			OrderId = orderId;
		}
		public String getProductName() {
			return ProductName;
		}
		public void setProductName(String productName) {
			ProductName = productName;
		}
		public String getSize() {
			return Size;
		}
		public void setSize(String size) {
			Size = size;
		}
		public String getColor() {
			return Color;
		}
		public void setColor(String color) {
			Color = color;
		}
		public String getItemNumber() {
			return ItemNumber;
		}
		public void setItemNumber(String itemNumber) {
			ItemNumber = itemNumber;
		}
		public Integer getQuantity() {
			return Quantity;
		}
		public void setQuantity(Integer quantity) {
			Quantity = quantity;
		}
		public Integer getPrice() {
			return Price;
		}
		public void setPrice(Integer price) {
			Price = price;
		}
		public Integer getSales() {
			return Sales;
		}
		public void setSales(Integer price,Integer quantity) {
			Sales = price*quantity;
		}
		
		
		
}
