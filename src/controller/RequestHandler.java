package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
	
	public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

}
