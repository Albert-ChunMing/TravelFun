package tf.endmodel;

public class endProductColors {
	private int product_id;
	private String color_name;
	private String photo_main_url;
	private String photo_url1;
	private String photo_url2;
	private String photo_url3;
	private String photo_url4;
	private String item_number;
	public endProductColors(int product_id, String color_name, String photo_main_url, String photo_url1, String photo_url2,
			String photo_url3, String photo_url4, String item_number) {
		super();
		this.product_id = product_id;
		this.color_name = color_name;
		this.photo_main_url = photo_main_url;
		this.photo_url1 = photo_url1;
		this.photo_url2 = photo_url2;
		this.photo_url3 = photo_url3;
		this.photo_url4 = photo_url4;
		this.item_number = item_number;
		
	}
	public endProductColors() {
		super();
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getColor_name() {
		return color_name;
	}
	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}
	public String getPhoto_main_url() {
		return photo_main_url;
	}
	public void setPhoto_main_url(String photo_main_url) {
		this.photo_main_url = photo_main_url;
	}
	public String getPhoto_url1() {
		return photo_url1;
	}
	public void setPhoto_url1(String photo_url1) {
		this.photo_url1 = photo_url1;
	}
	public String getPhoto_url2() {
		return photo_url2;
	}
	public void setPhoto_url2(String photo_url2) {
		this.photo_url2 = photo_url2;
	}
	public String getPhoto_url3() {
		return photo_url3;
	}
	public void setPhoto_url3(String photo_url3) {
		this.photo_url3 = photo_url3;
	}
	public String getPhoto_url4() {
		return photo_url4;
	}
	public void setPhoto_url4(String photo_url4) {
		this.photo_url4 = photo_url4;
	}
	public String getItem_number() {
		return item_number;
	}
	public void setItem_number(String item_number) {
		this.item_number = item_number;
	}
	
	
	
	
}
