package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
		String CustomerId,OrderTime,Recipient,Phone,Address,StatusCode;
		Integer OrderId,TotalSales;
		public Order(Integer orderId, String customerId, String orderTime, String recipient, String phone,
				String address, String statusCode, Integer totalSales) {
			super();
			OrderId = orderId;
			CustomerId = customerId;
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyyMMddHHmmss");
			OrderTime = sdf.format(new Date());
			Recipient = recipient;
			Phone = phone;
			Address = address;
			StatusCode = statusCode;
			TotalSales = totalSales;
		}
		public Order() {
			super();
		}
		public Integer getOrderId() {
			return OrderId;
		}
		public void setOrderId(Integer orderId) {
			OrderId = orderId;
		}
		public String getCustomerId() {
			return CustomerId;
		}
		public void setCustomerId(String customerId) {
			CustomerId = customerId;
		}
		public String getOrderTime() {
			return OrderTime;
		}
		public void setOrderTime(String orderTime) {
			OrderTime = orderTime;
		}
		public String getRecipient() {
			return Recipient;
		}
		public void setRecipient(String recipient) {
			Recipient = recipient;
		}
		public String getPhone() {
			return Phone;
		}
		public void setPhone(String phone) {
			Phone = phone;
		}
		public String getAddress() {
			return Address;
		}
		public void setAddress(String address) {
			Address = address;
		}
		public String getStatusCode() {
			return StatusCode;
		}
		public void setStatusCode(String statusCode) {
			StatusCode = statusCode;
		}
		public Integer getTotalSales() {
			return TotalSales;
		}
		public void setTotalSales(Integer totalSales) {
			TotalSales = totalSales;
		}
		
		
}
