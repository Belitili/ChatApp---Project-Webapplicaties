package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;

import db.UserDB;
import model.ChatConversation;
import model.User;

/**
 * Servlet implementation class GetChatServlet
 */
@WebServlet("/GetChatServlet")
public class GetChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetChatServlet() {
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
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");
		UserDB db = UserDB.getDB();
		currentUser = db.get(currentUser.getUsername());
		ChatConversation conv = db.getConversation(friendUserName, currentUser.getUsername());

		try {
			response.getWriter().write(conv.toJSON().toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
