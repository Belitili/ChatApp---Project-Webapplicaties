package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.UserDB;
import model.ChatConversation;
import model.User;

/**
 * Servlet implementation class SendMessageServlet
 */
@WebServlet("/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String friendUserName = request.getParameter("frienduser");
		String message = request.getParameter("message");
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");
		UserDB db = UserDB.getDB();
		currentUser = db.get(currentUser.getUsername());
		User friendUser = db.get(friendUserName);
		ChatConversation conv = db.getConversation(friendUserName, currentUser.getUsername());
		conv.addMessage(currentUser, friendUser, message);
	}

}
