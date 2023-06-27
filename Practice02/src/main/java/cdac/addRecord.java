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
 * Servlet implementation class addRecord
 */
@WebServlet("/addRecord")
public class addRecord extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn;
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root", "Rahul123");
			String str = "INSERT INTO EMPLOYEE(name, email, pswd) values (?, ?, ?)";
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pswd = request.getParameter("pswd");
			
			PreparedStatement st = conn.prepareStatement(str);
			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, pswd);
			
			st.executeUpdate();
			
			out.write("<html><body>");
			out.write("<h4 style='text-align: center; color: red'>Record Successfully Added !</h4>");
			out.write("<a href='ViewCustomers'>Home</a>");
			out.write("</body></html>");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


}
