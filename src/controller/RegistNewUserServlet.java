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

import db.UserDB;
import model.UserFactory;

/**
 * Servlet implementation class RegistNewUserServlet
 */
@WebServlet("/RegistNewUserServlet")
public class RegistNewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistNewUserServlet() {
        super();
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
		UserDB userDB = UserDB.getDB();
		
		String username = request.getParameter("username").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		String passwordRepeat = request.getParameter("passwordRepeat").trim();
		String name = request.getParameter("name").trim();
		String fname = request.getParameter("fname").trim();
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("selectGender");

		if (age<8) {
			errors.add("You must be at least 8 years old to register");
		}	
		if (!passwordRepeat.equals(password)) {
			errors.add("Password and password repeat is not the same.");
		}	
		if (userDB.get(username)!=null) {
			errors.add("There is already a user with this username in our database.");
		}
		
		//These are already checked client-side by 'required' attribute, but extra check if this ever changes
		if (username == null || username.isEmpty()) {
			errors.add("No username given");
		}if (email == null || email.isEmpty()) {
			errors.add("No email given");
		}		
		if (password == null || password.isEmpty()) {
			errors.add("No password given");
		}		
		if (passwordRepeat == null || passwordRepeat.isEmpty()) {
			errors.add("No repeated password given");
		}	
		if (name == null || name.isEmpty()) {
			errors.add("No name given");
		}		
		if (fname == null || fname.isEmpty()) {
			errors.add("No first name given");
		}	
		if (gender == null || gender.isEmpty()) {
			errors.add("No gender given");
		}
		
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		if (errors.size() == 0) {
			userDB.add(UserFactory.createUser(username, password, email, name, fname, age, gender));
		}
		
		if (errors.size() > 0) {
			view = request.getRequestDispatcher("register.jsp");
			request.setAttribute("errors", errors);
		}
		
		view.forward(request, response);
	}

}
