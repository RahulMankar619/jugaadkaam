package cdac;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editForm
 */
@WebServlet("/editForm")
public class editForm extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn;
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root", "Rahul123");
			String str = "SELECT * FROM EMPLOYEE WHERE id = ?";
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			PreparedStatement st = conn.prepareStatement(str);
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				out.write("<html><body>");
				out.write("<form action='updateRecord'>");
				out.write("<input type='hidden' name='id' value='"+ rs.getInt("id") +"'><br>");
				out.write("Name : <input type='text' name='name' value='"+ rs.getString("name") +"'><br>");
				out.write("Email :  <input type='email' name='email' value='"+ rs.getString("email") +"'><br>");
				out.write("Password : <input type='password' name='pswd' value='"+ rs.getString("pswd") +"'><br>");
				out.write("<button type='submit'>Update</button>");
				out.write("</form>");
				out.write("</body></html>");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


}
