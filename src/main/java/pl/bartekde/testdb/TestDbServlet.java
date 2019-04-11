package pl.bartekde.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestDbServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// setup connection variables
				String user = "springstudent";
				String pass = "springstudent";
				
				String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
				String driver = "com.mysql.jdbc.Driver";
				
				// get connection to database
				try {
					
					PrintWriter out = response.getWriter();
					out.println("Connceting to database: " + jdbcUrl);
					
					Class.forName(driver);
					
					Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
					
					out.println("SUCCESS");
					
					myConn.close();
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
		
	}

}
