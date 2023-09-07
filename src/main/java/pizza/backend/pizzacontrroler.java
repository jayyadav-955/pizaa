package pizza.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/domin")
public class pizzacontrroler extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String myemail=req.getParameter("username");
		String mypass=req.getParameter("password");
		PrintWriter pw=resp.getWriter();
		resp.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/delhi","root","root");
			PreparedStatement ps=con.prepareStatement("select * from noida where email =? and passrd=?");
			ps.setString(1,myemail);
			ps.setString(2,mypass);
			ResultSet rs= ps.executeQuery();
		
		
		if(rs.next()) {
			HttpSession session=req.getSession();
			session.setAttribute("key","vv");
			RequestDispatcher rd=req.getRequestDispatcher("/MamaJiMeals/index.html");
			rd.include(req, resp);
		}
		else {
			resp.setContentType("text/html");
			pw.print("<h3 style=color:red> Your Email and password did'not match </h3>");
			RequestDispatcher rd=req.getRequestDispatcher("/Enter.html");
			rd.include(req, resp);
			
		}
		}
		catch(Exception e) {
			
		}
	}

}
