import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Gmail_reg extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Gmail_reg() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		 String fname=request.getParameter("fname");
		 String lname=request.getParameter("lname");
		 String uname=request.getParameter("uname");
		 String pass=request.getParameter("pass");
		 String cpass=request.getParameter("cpass");
		
		 String dburl="jdbc:mysql://localhost:3306/google?autoReconnect=true&useSSL=false";
		 String dbuser="root";
		 String dbpass="";
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(dburl,dbuser,dbpass);
			String q="insert into gmailreg values(?,?,?,?,?)";
			String q1="select *from gmailreg where username=?";
			PreparedStatement pst1=con.prepareStatement(q1);
			pst1.setString(1, uname);
			ResultSet rs=pst1.executeQuery();
			PreparedStatement pst=con.prepareStatement(q);
			pst.setString(1, fname);
			pst.setString(2, lname);
			if(rs.next()) {
				pw.print("Username alredy taken by someone!");
			}else {
			pst.setString(3, uname);
			}
			if(pass.equals(cpass)) {
			pst.setString(4, pass);
			pst.setString(5, cpass);
			}else {
				pw.write("Password not same please check your password!!!");
			}
			pst.execute();
			pw.write("regiterd success");
			pw.write("<a href='Loginpage.html'>Click to login</a>");
		} catch (Exception e) {	
			e.printStackTrace();
	
		}
	}

}
