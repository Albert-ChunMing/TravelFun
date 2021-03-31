package tf.endmodel;

public class endreplequery {
	private int id;
	private String name;
	private String namecolor;
	private String namenumber;
	private int stock;
	public endreplequery(int id, String name, String namecolor, String namenumber, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.namecolor = namecolor;
		this.namenumber = namenumber;
		this.stock = stock;
	}
	public endreplequery(String namecolor, String namenumber, int stock) {
		super();
		this.namecolor = namecolor;
		this.namenumber = namenumber;
		this.stock = stock;
	}
	public endreplequery() {
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
	public String getNamecolor() {
		return namecolor;
	}
	public void setNamecolor(String namecolor) {
		this.namecolor = namecolor;
	}
	public String getNamenumber() {
		return namenumber;
	}
	public void setNamenumber(String namenumber) {
		this.namenumber = namenumber;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
	
	
}
