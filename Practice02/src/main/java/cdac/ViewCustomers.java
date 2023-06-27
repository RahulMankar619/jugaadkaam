package cdac;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewCustomers
 */
@WebServlet("/ViewCustomers")
public class ViewCustomers extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn;
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root", "Rahul123");
			String str = "SELECT * FROM EMPLOYEE";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(str);
			
			out.write("<html><body>");
			
			out.write("<button><a href='Add.jsp'>Add Customer</a></button>");
			
			out.write("<table border='1' style='border-collapse: collapse;'>");
			out.write("<tr>");
			out.write("<td> ID </td>");
			out.write("<td> Name </td>");
			out.write("<td> Email </td>");
			out.write("<td> Edit </td>");
			out.write("<td> Delete </td>");
			out.write("</tr>");
			
			while(rs.next()) {
				
				out.write("<tr>");
				out.write("<td>"+ rs.getInt("id") +" </td>");
				out.write("<td>"+ rs.getString("name") +" </td>");
				out.write("<td>"+ rs.getString("email") +" </td>");
				out.write("<td>" + "<a href='editForm?id="+rs.getInt("id") + "'>Edit</a>" + "</td>");
				out.write("<td>" + "<a href='deleteRecord?id="+rs.getInt("id") + "'>Delete</a>" + "</td>");
				out.write("<tr>");

			}
			out.write("</table>");
			out.write("</body></html>");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}


}
