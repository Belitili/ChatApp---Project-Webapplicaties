package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.UserDB;
import model.User;

/**
 * Servlet implementation class AddFriendServlet
 * !! @MultipartConfig is because of FormData used in addFriend.js
 */
@WebServlet("/AddFriendServlet")
@MultipartConfig
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendServlet() {
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
		
		String friendToAdd = request.getParameter("friendToAdd");
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("user");
		
		String returnMessage;
		if (session.getAttribute("username").equals(friendToAdd)) {
			returnMessage = "You can't add yourself.";
		} else {
			UserDB userDB = UserDB.getDB();
			
			User user = userDB.get(friendToAdd);
			if (user==null) {
				returnMessage = "No user found by that username.";
			} else {
				boolean alreadyfriends = false;
				ArrayList<User> friends = currentUser.getFriends();
				for (User friend: friends) {
					if (friend.getUsername().equals(friendToAdd)) {
						alreadyfriends = true;
					}
				}
				if (alreadyfriends) {
					returnMessage = friendToAdd + " is already your friend.";
				} else {
					currentUser.addFriend(user);
					user.addFriend(userDB.get(currentUser.getUsername()));
					returnMessage = "User '" + friendToAdd + "' added to your friend list.";
					
				}
			}
		}
		response.getWriter().write(returnMessage); 
	}

}
