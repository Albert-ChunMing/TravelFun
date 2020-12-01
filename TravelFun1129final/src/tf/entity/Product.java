package tf.entity;



public class Product {
    private int id; //PKey,auto increment
	private String name; // required, Unique Index
    private double unitPrice; // required,定價即售價
    private String description=""; //optional
    private String class_name="";
    private int discount;
    private String category="";
    private String brand;
    private String colorName;
    private String photo_main_url; //optional
    private String photoUrl1;
    private String photoUrl2;
    private String photoUrl3;
    private String photoUrl4;
    private String photoUrl5;
    private String itemnumber;
    private int stock; // required
    private String sizeName;
	private String sizeNumber;
	private int sizeStock;
    
   public Product() {} //若設計人員沒有定義建構式，編譯器會自行加上預設建構式，預設建構式沒有定義參數且內容為空白
	
    public Product(int id,String name,double Price){
    	this.id=id;
    	this.name=name;
    	this.unitPrice=Price;
    	
    }
    
    public Product(int id, String name, double unitPrice, int stock) {
		//super();
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.stock = stock;
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
	        this.name =name ;
	        }
	
	
	public double getUnitPrice() { //查詢定價即售價
		return unitPrice;
	}


	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getPhoto_main_url() {
		return photo_main_url;
	}

	public void setPhoto_main_url(String photo_main_url) {
		this.photo_main_url = photo_main_url;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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

	
	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getPhotoUrl1() {
		return photoUrl1;
	}

	public void setPhotoUrl1(String photoUrl1) {
		this.photoUrl1 = photoUrl1;
	}

	public String getPhotoUrl2() {
		return photoUrl2;
	}

	public void setPhotoUrl2(String photoUrl2) {
		this.photoUrl2 = photoUrl2;
	}

	public String getPhotoUrl3() {
		return photoUrl3;
	}

	public void setPhotoUrl3(String photoUrl3) {
		this.photoUrl3 = photoUrl3;
	}

	public String getPhotoUrl4() {
		return photoUrl4;
	}

	public void setPhotoUrl4(String photoUrl4) {
		this.photoUrl4 = photoUrl4;
	}

	public String getPhotoUrl5() {
		return photoUrl5;
	}

	public void setPhotoUrl5(String photoUrl5) {
		this.photoUrl5 = photoUrl5;
	}

	public String getItemnumber() {
		return itemnumber;
	}

	public void setItemnumber(String itemnumber) {
		this.itemnumber = itemnumber;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getSizeNumber() {
		return sizeNumber;
	}

	public void setSizeNumber(String sizeNumber) {
		this.sizeNumber = sizeNumber;
	}

	public int getSizeStock() {
		return sizeStock;
	}

	public void setSizeStock(int sizeStock) {
		this.sizeStock = sizeStock;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount=discount;
		}
	
	/*public String getDiscountString() {
		int discount = 100-this.discount;
		if(discount%10==0) {
			discount = discount/10;
		}
		return discount + "折";
	} */
	
}
