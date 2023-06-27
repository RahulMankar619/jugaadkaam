package cdac;

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

/**
 * Servlet implementation class updateRecord
 */
@WebServlet("/updateRecord")
public class updateRecord extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn;
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root", "Rahul123");
			String str = "UPDATE EMPLOYEE SET name = ?, email = ?, pswd= ? WHERE id = ?";
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pswd = request.getParameter("pswd");
			int id = Integer.parseInt(request.getParameter("id"));
			
			PreparedStatement st = conn.prepareStatement(str);
			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, pswd);
			st.setInt(4, id);
			
			st.executeUpdate();
			
			out.write("<html><body>");
			out.write("<h4 style='text-align: center; color: red'>Record Successfully Updated !</h4>");
			out.write("<a href='ViewCustomers'>Home</a>");
			out.write("</body></html>");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


}
