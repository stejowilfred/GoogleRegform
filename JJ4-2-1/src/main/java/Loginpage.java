import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Loginpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Loginpage() {
        super();
        }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher rd=request.getRequestDispatcher("Home.html");
		PrintWriter pw=response.getWriter();
		HttpSession sess=request.getSession();
		String uname=request.getParameter("uname");
		String pass=request.getParameter("pass");
		sess.setAttribute("Uname", uname);
		sess.setAttribute("upass", pass);
		sess.setMaxInactiveInterval(180);
		 String dburl="jdbc:mysql://localhost:3306/google?autoReconnect=true&useSSL=false";
		 String dbuser="root";
		 String dbpass="";
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(dburl,dbuser,dbpass);
			String q="select * from gmailreg where username =? and password=?";
			PreparedStatement pst =con.prepareStatement(q);
			pst.setString(1, uname);
			pst.setString(2, pass);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				
				sess.setAttribute("USER", uname);
				response.sendRedirect("Home.html");
//				rd.forward(request, response);
			}else {
				pw.write("check your password or username");
			}
			
		 }
		 catch(Exception e) {
			 e.toString();
		 }
	}
	
}
