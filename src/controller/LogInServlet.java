package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.UserDB;
import model.User;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();
		System.out.println("loginServlet--");
		String username = request.getParameter("username");
		if (username == null || username.isEmpty()) {
			errors.add("No username given");
		}
		
		String password = request.getParameter("password");
		if (password == null || password.isEmpty()) {
			errors.add("No password given");
		}
		
		if (errors.size() == 0) {
			UserDB userDB = UserDB.getDB();
			User user = userDB.get(username);
			if (user != null && user.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setAttribute("username", user.getUsername());
				session.setAttribute("status", "online");
				user.setStatus("online");
			} else {
				errors.add("No valid email/password");
			}
		}
		
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

}
