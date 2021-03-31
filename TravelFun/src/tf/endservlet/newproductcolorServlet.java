package tf.endservlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import tf.endmodel.endProductColors;



/**
 * Servlet implementation class newproductcolorServlet
 */
@WebServlet("/newproductcolorServlet")
public class newproductcolorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newproductcolorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		SmartUpload su = new SmartUpload();
		//SmartUpload smartUpload = new SmartUpload();
		PageContext pagecontext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		su.initialize(pagecontext);
		try {
			//su.setAllowedFilesList("jpg");
			su.upload();
			// String str = new String(name.getBytes("gbk"), "utf-8");    传值过程中出现乱码，在此转码
			 //     IPTimeStamp its = new IPTimeStamp("192.168.1.1") ;   取得客户端的IP地址
		
			String[] url=new String[5];
			/*String BF="ProductPhoto/BF/";
			String LO="ProductPhoto/LO/";
			String CB="ProductPhoto/CB/";
			String OS="ProductPhoto/OS/";
			String R8="ProductPhoto/R8/";
			String PF="ProductPhoto/PF/";
			String TB="ProductPhoto/TB/";
			String TI="ProductPhoto/TI/";*/
			
			String product_id=su.getRequest().getParameter("procolorproduct_id");
			int id=Integer.parseInt(product_id);
			String color_name=su.getRequest().getParameter("procolorcolor_name");
			File urll = su.getFiles().getFile(0);
			/*String url="C:\\we-ee2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\newWork\\share\\"+urll.getFileName();
			File url11 = su.getFiles().getFile(1);
			String url1="C:\\we-ee2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\newWork\\share\\"+url11.getFileName();
			File url22 = su.getFiles().getFile(2);
			String url2="C:\\we-ee2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\newWork\\share\\"+url22.getFileName();
			File url33 = su.getFiles().getFile(3);
			String url3="C:\\we-ee2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\newWork\\share\\"+url33.getFileName();
			File url44 = su.getFiles().getFile(4);
			String url4="C:\\we-ee2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\newWork\\share\\"+url44.getFileName();
			*/
			String item_number=su.getRequest().getParameter("procoloritem_number");
						
			for(int i=0;i<su.getFiles().getCount();i++)
			{
				
				File url11 = su.getFiles().getFile(i);
				if(url11.isMissing()) continue;
				url[i]="ProductPhoto/"+url11.getFileName().substring(0,2)+"/"+url11.getFileName();
				/*if(url11.getFileName().substring(0,2).equals("BF"))
					url[i]=BF+url11.getFileName();
				if(url11.getFileName().substring(0,2).equals("LO"))
					url[i]=LO+url11.getFileName();
				if(url11.getFileName().substring(0,2).equals("CB"))
					url[i]=CB+url11.getFileName();
				if(url11.getFileName().substring(0,2).equals("OS"))
					url[i]=OS+url11.getFileName();
				if(url11.getFileName().substring(0,2).equals("R8"))
					url[i]=R8+url11.getFileName();
				if(url11.getFileName().substring(0,2).equals("PF"))
					url[i]=PF+url11.getFileName();
				if(url11.getFileName().substring(0,2).equals("TB"))
					url[i]=TB+url11.getFileName();
				if(url11.getFileName().substring(0,2).equals("TI"))
					url[i]=TI+url11.getFileName();*/

			}
			
			String Submit=su.getRequest().getParameter("Submit");
			endProductColors p=new endProductColors(id,color_name,url[0],url[1],url[2],url[3],url[4],item_number);
			if(Submit.equals("新增"))
			{add(p);}
			if(Submit.equals("刪除"))
			{delete(p);}
			
			
			
			/*if()
			{
				su.save("/ProductPhoto/BF");
			}else
				
			if(su.getFiles().getFile(0).getFileName().substring(0,2).equals("LO"))
				su.save("/ProductPhoto/LO");
			if(su.getFiles().getFile(0).getFileName().substring(0,2).equals("CB"))
				su.save("/ProductPhoto/CB");
			if(su.getFiles().getFile(0).getFileName().substring(0,2).equals("OS"))
				su.save("/ProductPhoto/OS");
			if(su.getFiles().getFile(0).getFileName().substring(0,2).equals("R8"))
				su.save("/ProductPhoto/R8");
			if(su.getFiles().getFile(0).getFileName().substring(0,2).equals("PF"))
				su.save("/ProductPhoto/PF");
			if(su.getFiles().getFile(0).getFileName().substring(0,2).equals("TB"))
				su.save("/ProductPhoto/TB");
			if(su.getFiles().getFile(0).getFileName().substring(0,2).equals("TI"))
				su.save("/ProductPhoto/TI");*/
			
			//onclick="location.href='要前往的網頁連結'">
			String path="C:\\Users\\User\\Desktop\\ocp\\EclipseEE workplace\\TravelFun1127\\WebContent\\ProductPhoto\\"+su.getFiles().getFile(0).getFileName().substring(0,2);			
			String path2="C:\\Users\\User\\Desktop\\ocp\\EclipseEE workplace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TravelFun1127\\ProductPhoto\\"+su.getFiles().getFile(0).getFileName().substring(0,2);
			String savefile = java.getpath(path,path2);
			System.out.println(savefile);
			su.save("/ProductPhoto/"+su.getFiles().getFile(0).getFileName().substring(0,2));
			
			out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>新增商品資訊</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("</head>");
            out.println("<body>");
			out.println("<input type=button id=ss value=回上頁 onclick=history.back() ; >");
			out.println("<div >");
			for(int i=0;i<su.getFiles().getCount();i++ ){
				File file = su.getFiles().getFile(i);
				if(file.isMissing()) continue;
				
				
				out.println("<img src=ProductPhoto/"+file.getFileName().substring(0,2)+"/"+file.getFileName()+" border=1 width=200px height=200px>");
				out.println("<table border=1>");
				out.println("<tr><td>檔案大小</td><td>"+ file.getSize()+ "</td></tr>");
				out.println("<tr><td>檔名</td><td>" +file.getFileName()+ "</td></tr>");
				out.println("<tr><td>副檔名</td><td>"+ file.getFileExt()+ "</td></tr>");
				out.println("<tr><td>選取路徑</td><td>"+ file.getFilePathName() +"</td></tr>");
				out.println("</table><br>");
				
				}
			out.println("</div>");
			out.println("<script>");
			//out.println("alert();");
			//out.println("function ssshow(){ var div1 = document.getElementById(show); alert(div1);}");
			//out.println("function ssshow(){ var div1 = document.getElementById(show); alert(div1);}");
			out.println("function ssshow(){var div1 = document.getElementById(show); div1.style.display=none;}");
			out.println("</script>");
			out.println("</body>");
			 out.println("</html>");
			
			 //smartUpload.initialize(this.getServletConfig(), request, response);
			//smartUpload.setContentDisposition(null);  //設定contentDisposition為null以禁止瀏覽器自動開啟檔案
			 //smartUpload.downloadFile("C:\\we-ee2\\newWork\\share"+fileName);
			} catch (SmartUploadException e) {
			e.printStackTrace();
			}
		
	}
	
	void delete(endProductColors p)
	{
		Connection con=null;
		PreparedStatement st=null;
		String sql="delete from tf.Product_Colors where product_id=? and color_name=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setInt(1,p.getProduct_id());
			st.setString(2,p.getColor_name());
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	void add(endProductColors p)
	{
		
		Connection con=null;
		PreparedStatement st=null;
		String sql="insert into tf.Product_Colors(product_id,color_name,photo_main_url,photo_url1,photo_url2,photo_url3,photo_url4,item_number) values(?,?,?,?,?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=utf-8&serverTimezone=CST","root","1234");
			st=con.prepareStatement(sql);
			st.setInt(1,p.getProduct_id());
			st.setString(2,p.getColor_name());
			st.setString(3,p.getPhoto_main_url());
			st.setString(4,p.getPhoto_url1());
			st.setString(5,p.getPhoto_url2());
			st.setString(6,p.getPhoto_url3());
			st.setString(7,p.getPhoto_url4());
			st.setString(8,p.getItem_number());
			
			st.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
