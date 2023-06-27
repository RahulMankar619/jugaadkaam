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
 * Servlet implementation class deleteRecord
 */
@WebServlet("/deleteRecord")
public class deleteRecord extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn;
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root", "Rahul123");
			String str = "DELETE FROM EMPLOYEE WHERE id = ?";

			int id = Integer.parseInt(request.getParameter("id"));
			
			PreparedStatement st = conn.prepareStatement(str);
			st.setInt(1, id);
			
			st.executeUpdate();
			
			out.write("<html><body>");
			out.write("<h4 style='text-align: center; color: red'>Record Deleted !</h4>");
			out.write("<a href='ViewCustomers'>Home</a>");
			out.write("</body></html>");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
