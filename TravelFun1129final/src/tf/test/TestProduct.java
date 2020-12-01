package tf.test;

import tf.entity.Product;

public class TestProduct {

	public static void main(String[] args) {
		
    Product p = new Product();
    
    p.setId(1);
    p.setName("舒適素色-美麗諾羊毛頭巾");
    p.setUnitPrice(980);
    p.setPhoto_main_url("https://c.ecimg.tw/items/DEBLGBA9009O2VA/000001_1545207263.jpg");
    p.setDescription("+ Korus專業系列針對戶外玩家設計，適合登山健行或大雨騎乘機車使用");
	
	System.out.println("ID: "+p.getId());
	System.out.println("name: "+p.getName());
	System.out.println("price: "+p.getUnitPrice());
	System.out.println("photo: " +p.getPhoto_main_url());
	System.out.println("description: "+p.getDescription());
	
	
	
	}

}
