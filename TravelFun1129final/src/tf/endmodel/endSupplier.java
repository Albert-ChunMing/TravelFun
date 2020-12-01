package tf.endmodel;

public class endSupplier {
	private int id;
	private String companyname;
	private String address;
	private String email;
	private String phone;
	private String salesName;
	private String salesPhone;
	private String salesEmail;
	public endSupplier(int id, String companyname, String address, String email, String phone, String salesName,
			String salesPhone, String salesEmail) {
		super();
		this.id = id;
		this.companyname = companyname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.salesName = salesName;
		this.salesPhone = salesPhone;
		this.salesEmail = salesEmail;
	}
	
	public endSupplier(String companyname, String address, String email, String phone, String salesName, String salesPhone,
			String salesEmail) {
		super();
		this.companyname = companyname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.salesName = salesName;
		this.salesPhone = salesPhone;
		this.salesEmail = salesEmail;
	}

	public endSupplier() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	public String getSalesPhone() {
		return salesPhone;
	}
	public void setSalesPhone(String salesPhone) {
		this.salesPhone = salesPhone;
	}
	public String getSalesEmail() {
		return salesEmail;
	}
	public void setSalesEmail(String salesEmail) {
		this.salesEmail = salesEmail;
	}
	
	
	
	
}
