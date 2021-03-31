package tf.endmodel;

public class endProduct {
	private int id;
	private String name;
	private double unit_price;
	private String description;
	private String class_name;
	private int discount;
	private String category;
	private String brand;
	private int prosupplier;
	

	public endProduct(int id, String name, double unit_price, String description, String class_name, int discount,
			String category, String brand,int prosupplier) {
		super();
		this.id = id;
		this.name = name;
		this.unit_price = unit_price;
		this.description = description;
		this.class_name = class_name;
		this.discount = discount;
		this.category = category;
		this.brand = brand;
		this.prosupplier = prosupplier;
	}

	public endProduct(String name, double unit_price, String description, String class_name, int discount, String category,
			String brand, int prosupplier) {
		super();
		this.name = name;
		this.unit_price = unit_price;
		this.description = description;
		this.class_name = class_name;
		this.discount = discount;
		this.category = category;
		this.brand = brand;
		this.prosupplier = prosupplier;
	}

	public endProduct() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getProsupplier() {
		return prosupplier;
	}

	public void setProsupplier(int prosupplier) {
		this.prosupplier = prosupplier;
	}

	
}
