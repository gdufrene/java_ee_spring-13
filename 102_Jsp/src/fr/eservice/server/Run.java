package fr.eservice.server;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Run {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		WebAppContext context = new WebAppContext();
		context.setContextPath( "/" );
		context.setResourceBase( "www" );
		context.setInitParameter( "dirAllowed", "true" );
        
		context.setInitParameter("logVerbosityLevel", "DEBUG");
		context.setInitParameter("fork", "false");
		context.setInitParameter("keepgenerated", "true");
		
		server.setHandler(context);
		
		System.setProperty("org.eclipse.jetty.util.UrlEncoding.charset", "UTF8");
		System.setProperty("org.mortbay.util.UrlEncoding.charset", "UTF8");

		server.start();
		server.join();
	}
	

	
}
