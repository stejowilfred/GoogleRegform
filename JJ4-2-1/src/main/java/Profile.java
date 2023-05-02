import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Profile() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		HttpSession sess=request.getSession(false);
		String name=(String) sess.getAttribute("uname");
	    String pass=(String) sess.getAttribute("upass");
	    pw.write("User name : "+name+"<br>");
	    pw.write("password : "+pass+"<br>");
	    pw.write("Session inactive time : "+sess.getMaxInactiveInterval()+"<br>");
	    pw.write("Session Id:"+sess.getId()+"<br>");
	}

}
