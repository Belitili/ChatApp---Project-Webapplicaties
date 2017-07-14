package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class changeStatusServlet
 * !! @MultipartConfig is because of FormData used in sendStatus.js
 */
@WebServlet("/ChangeStatusServlet")
@MultipartConfig
public class ChangeStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeStatusServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String statusSelect = request.getParameter("selectStatus");
		String statusCustom = request.getParameter("customStatus");
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");
		
		String newstatus = currentUser.getStatus();
		
		if(!statusCustom.trim().isEmpty()) {
			newstatus = statusCustom;
		} else {
			newstatus = statusSelect;
		}

		currentUser.setStatus(newstatus);
		session.setAttribute("status", newstatus);
		response.getWriter().write(newstatus); 
	}

}
