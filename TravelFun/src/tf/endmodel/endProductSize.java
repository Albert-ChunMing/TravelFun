package tf.endmodel;

public class endProductSize {
	private String color_name;
	private String s_name;
	private String s_number;
	private int s_stock;
	public endProductSize(String color_name, String s_name, String s_number, int s_stock) {
		super();
		this.color_name = color_name;
		this.s_name = s_name;
		this.s_number = s_number;
		this.s_stock = s_stock;
	}
	
	public endProductSize(String color_name, String s_name, int s_stock) {
		super();
		this.color_name = color_name;
		this.s_name = s_name;
		this.s_stock = s_stock;
	}

	public endProductSize() {
		super();
	}
	public String getColor_name() {
		return color_name;
	}
	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_number() {
		return s_number;
	}
	public void setS_number(String s_number) {
		this.s_number = s_number;
	}
	public int getS_stock() {
		return s_stock;
	}
	public void setS_stock(int s_stock) {
		this.s_stock = s_stock;
	}


	
	
	
	
}
