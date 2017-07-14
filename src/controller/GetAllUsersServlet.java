package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import db.UserDB;
import model.User;

/**
 * Servlet implementation class GetAllUsersServlet
 */
@WebServlet("/GetAllUsersServlet")
public class GetAllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllUsersServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDB db = new UserDB();
		
		JSONArray array = new JSONArray();
		for (User users: db.getAll()) {
			try {
				array.put(users.getJSONObject());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// setting the response type to json
		response.setContentType("application/json");
		// setting the CORS request, CrossOriginResourceSharing
		// so that this url/response is accessible in another domain (angular application for example) 
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.getWriter().write(array.toString());
	}

}
