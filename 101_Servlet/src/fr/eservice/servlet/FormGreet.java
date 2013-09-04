package fr.eservice.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormGreet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		Writer out = resp.getWriter();
		out.write("<html>");
		out.write("<head>");
		out.write("<title>Hello Form Servlet</title>");
		out.write("</head>");
		out.write("<body>");
		out.write("<form action='/form-greet' method='post'>");
		out.write("Votre nom : ");
		out.write("<input type='text' name='name' width='40'/>");
		out.write("<input type='submit' value='Ok'/>");
		out.write("</form>");
		out.write("</body>");
		out.write("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		if ( name == null || name.isEmpty() ) name = "world";
		
		Writer out = resp.getWriter();
		out.write("<html>");
		out.write("<head>");
		out.write("<title>Hello Form Servlet</title>");
		out.write("</head>");
		out.write("<body>");
		
		// TODO: Afficher Bonjour `name` !
		out.write("<b>COMPLETE THIS CODE</b><br/>");
		
		out.write("</body>");
		out.write("</html>");
	}
	
}
