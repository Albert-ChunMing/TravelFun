package tf.endservlet;

import java.io.File;

public class java {
	public static void main(String[] args) {
		   // TODO Auto-generated method stub
			String t1="BF.20201126.jpg";
			String path="ProductPhoto\\"+t1;
		   String t="C:\\we-ee2\\new20201124\\WebContent\\ProductPhoto\\GG";
		   /*GetSql(path);*/
		   //getpath(t);
		  
		   
		 }
	public static String getpath(String path,String path2){
		
		File file =new File(path); 
		File file2 =new File(path2); 
		 //如果資料夾不存在則建立    
		System.out.println(file);
		
		 if  (!file .exists()  && !file .isDirectory())      
		 {       
		     System.out.println("//不存在");  
		     file .mkdir();  
		     file2.mkdir();
		 } else   
		 {  
		     System.out.println("//目錄存在");  
		 }  
		   
		   return file.toString();
		
	}
	
	public static  String GetSql(String path){
		  File rootDir = new File(path);
		  System.out.println(rootDir);
		   if(!rootDir.isDirectory()){
			
		    System.out.println("檔名"+rootDir.getAbsolutePath());
		   }else{
		    String[] fileList =  rootDir.list();
		    for (int i = 0; i < fileList.length; i++) {
		     path = rootDir.getAbsolutePath()+"\\"+fileList[i];
		     GetSql(path);      
		      }
		   }   
		  return null;   
		 }
	
	
	
}
