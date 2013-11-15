package portal;

import java.io.File;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import fr.eservice.web.Config;



public class TestServer {

	public static Handler springWebAppContext() {
		final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(Config.class);
	      final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
	      final ServletContextHandler context = new ServletContextHandler();

	      // context.setErrorHandler(null); // use Spring exception handler(s)
	      context.setContextPath("/");
	      context.addServlet(servletHolder, "/portal/*");
	      
	      
	      return context;
	}
	
	public static Handler jettyAnnotationContext() {
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        //webapp.setResourceBase(".");
        /* */
        webapp.addEventListener( new ContextLoaderListener() );
        webapp.setInitParameter("contextConfigLocation", "WEB-INF/spring/root-context.xml");
        /* */
        // webapp.addServerClass(WebAppInit.class.getName());
        webapp.setConfigurations( new Configuration[] { new AnnotationConfiguration() } );
        return webapp; 
	}
	
	public static Handler webXmlContext() {
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setDescriptor("WEB-INF/web.xml");
        webapp.setResourceBase(".");
        return webapp; 
	}
	
    public static ServletContextHandler getJetty9Handler() {
    	
    	final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(Config.class);
    	
        ServletHolder mvcServletHolder = new ServletHolder("mvcDispatcher", new DispatcherServlet(applicationContext));	
        // mvcServletHolder.setInitParameter("contextConfigLocation", "WEB-INF/spring/servlet-context.xml");

        ServletHolder jspServletHolder = new ServletHolder("jspServlet", new org.apache.jasper.servlet.JspServlet());
        // these two lines are not strictly required - they will keep classes generated from JSP in "${javax.servlet.context.tempdir}/views/generated"
        jspServletHolder.setInitParameter("keepgenerated", "true");
        jspServletHolder.setInitParameter("scratchDir", "views/generated");

        // session has to be set, otherwise Jasper won't work
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setAttribute("javax.servlet.context.tempdir", new File("tmp"));
        // that classloader is requres to set JSP classpath. Without it you will just get NPE
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.addServlet(jspServletHolder, "*.jsp");
        context.addServlet(mvcServletHolder, "/");
        context.setResourceBase( "www" );

        return context;
    }
	
	public static void main(String[] args) throws Exception {
		final Server server = new Server(8080);
		
		Handler handler = getJetty9Handler();
		
		/*
		WebAppContext context = new WebAppContext();
		context.setInitParameter( "dirAllowed", "true" );
		context.setInitParameter("logVerbosityLevel", "DEBUG");
		context.setInitParameter("fork", "false");
		context.setInitParameter("keepgenerated", "true");
		context.setResourceBase(".");
		
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { handler, context });
        */
		
		server.setHandler(handler);		
		server.start();
		server.join();
	}

}
