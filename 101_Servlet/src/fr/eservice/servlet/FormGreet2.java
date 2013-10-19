package fr.eservice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormGreet2 extends FormGreet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		
		// TODO: Complete code
		resp.getWriter().write( "TODO..." );
		
		// Open file www/form.html
		// read content from file
		// write content to response

	}

}
