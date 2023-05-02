
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

public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Remove() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess=request.getSession(false);
		PrintWriter pw=response.getWriter();
		String uname1=(String) sess.getAttribute("USER");
		
		 String dburl="jdbc:mysql://localhost:3306/google?autoReconnect=true&useSSL=false";
		 String dbuser="root";
		 String dbpass="";
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con=DriverManager.getConnection(dburl,dbuser,dbpass);
			 String q="delete from gmailreg where username=?";
			 PreparedStatement pst=con.prepareStatement(q);
			 pst.setString(1, uname1);
			 pst.executeUpdate();
			 pw.write("Userdelete success!!");
		 }
		 catch(Exception e) {
			 e.toString();
		 }
		 
	}

}
