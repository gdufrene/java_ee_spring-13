package fr.eservice.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import fr.eservice.servlet.Circonference;
import fr.eservice.servlet.FirstServlet;
import fr.eservice.servlet.FormGreet;
import fr.eservice.servlet.FormGreet2;

public class Run {

	public static void main(String[] args) throws Exception {
		
		Server server = new Server(8080);
		
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		server.setHandler(context);
		
		// http://localhost:8080/
		context.addServlet(FirstServlet.class.getName(), "/");
		
		context.addServlet(Circonference.class.getName(), "/circ");
		context.addServlet(FormGreet.class.getName(), "/hello");
		context.addServlet(FormGreet2.class.getName(), "/hello2");
		
		System.setProperty("org.eclipse.jetty.util.UrlEncoding.charset", "UTF8");
		System.setProperty("org.mortbay.util.UrlEncoding.charset", "UTF8");
		
		server.start();
		server.join();
	}
	
}
