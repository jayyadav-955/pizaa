package pizza.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/regispizaa")
public class Dtata  extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String myid=req.getParameter("id");
		String myname=req.getParameter("nme");
		String mylanm=req.getParameter("lanm");
		String myemail=req.getParameter("eml");
		String mypass=req.getParameter("pass");
		String myage=req.getParameter("age");
		String mygender=req.getParameter("gender1");
		String mycity=req.getParameter("city");
		
		
		PrintWriter pw=resp.getWriter();
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/delhi","root","root");
		
			
			PreparedStatement ps=con.prepareStatement("insert into noida values(?,?,?,?,?,?,?,?)");
			ps.setString(1, myid);
			ps.setString(2,myname);
			ps.setString(3,mylanm);
			ps.setString(4,myemail);
			ps.setString(5, mypass);
			ps.setString(6,myage);
			ps.setString(7,mygender);
			ps.setString(8,mycity);
			
			
			int max= ps.executeUpdate();
			if(max>0) {
				HttpSession session=req.getSession();
				session.setAttribute("key","vv");
				resp.setContentType("text/html");
				pw.print("<h3 style = 'color:green'> You Register  Successfully</br> you can login </h3>");
				
				RequestDispatcher rd= req.getRequestDispatcher("/Register.html");
				rd.include(req, resp);
			}
			else {
				resp.setContentType("text/html");
				pw.print("<h3 style = 'color:red'> you Can't Register  </h3>");
				RequestDispatcher rd= req.getRequestDispatcher("/Register.html");
				rd.include(req, resp);
			}
					
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			resp.setContentType("text/html");
			pw.print("<h3 style = 'color:red'> some Exception"+e+"  </h3>");
			RequestDispatcher rd= req.getRequestDispatcher("/Register.html");
			rd.include(req, resp);
			}
			
		}
		
		
		
		
	

}
