package userProcess;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginHandler
 */
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		System.out.println("username :" + username + ", password : "+password);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","lnknpark10");
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from users");
			while(rs.next()){
				String usernameRS = rs.getString(2);
				String passwordRS = rs.getString(3);
				System.out.println(usernameRS + " : " + passwordRS);
				if(username.equalsIgnoreCase(usernameRS)){
					if(password.equals(passwordRS))
					{	
						System.out.println("Logged in successfully");
						return;
					}
					else{
						System.out.println("Password didnt match");
						return;
					}
					
				}
				
			}
			System.out.println("Please check username/password again..");
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
