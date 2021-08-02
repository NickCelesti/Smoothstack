
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class Member {
	public String userName;
	public String password;

	public Member(String u, String p) {
		userName = u;
		password = p;
	}
}

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1712177217716046389L;
	Member[] members = new Member[5];

	public void init() throws ServletException {
		members[0] = new Member("a", "b");
		members[1] = new Member("Nick", "602645");
		members[2] = new Member("admin", "admin");
		members[3] = new Member("password", "password");
		members[4] = new Member("n", "n");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter writer = response.getWriter();
		writer.println("<html>Hello, this is a doGet!</html>");
		writer.flush();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		String paramUser = request.getParameter("userName");

		String paramPass = request.getParameter("password");
		boolean isUser = false;

		for (Member m : members) {
			if (m.userName.compareToIgnoreCase(paramUser) == 0 && m.password.compareTo(paramPass) == 0) {
				isUser = true;
			}
		}
		PrintWriter writer = response.getWriter();
		if (isUser) {
			writer.println("Successfully logged in as user: " + paramUser);
		} else {
			writer.println("Not a valid user");
		}

		writer.flush();

	}
}