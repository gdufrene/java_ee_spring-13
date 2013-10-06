package fr.eservice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Circonference extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doRequest(req, resp);
	}

	protected void doRequest( HttpServletRequest req, HttpServletResponse resp ) 
		throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		String rayon = req.getParameter("rayon");
		if ( rayon == null || rayon.isEmpty() ) {
			resp.setContentType("text/plain");
			resp.getWriter().write( 
				req.getRequestURI() + " Utilisation:\n" +
				"\thttp://" + req.getServerName() + ":" + req.getServerPort() + req.getRequestURI() + "?rayon=X\n" +
				"\tretourne la circonférence d'un cercle dont le rayon est donné.\n" +
				"\trayon: un nombre flottant\n"
			);
			return;
		}
		
		double d;
		try {
			d = Double.parseDouble(rayon);
		} catch( NumberFormatException exp ) {
			resp.setContentType("text/plain");
			resp.getWriter().write( "Le rayon indiqué ["+rayon+"] ne peut pas être lu.\n" );
			return;
		}
		
		resp.setContentType("application/json");
		
		// TODO: Use 'd' to compule circonference
		double circonference = 0.0;
		
		resp.getWriter().write( "{ 'circonference': " + circonference + " }\n" );
	}

}
