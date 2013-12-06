package portal;

import java.io.File;
import java.util.Arrays;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.deploy.providers.WebAppProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import fr.eservice.web.Config;



public class TestServer {
	
	public static void configureHotDeployement( Server server, ContextHandler context ) {
		 DeploymentManager deploymentManager = new DeploymentManager();
	        
	        ContextHandlerCollection contexts = new ContextHandlerCollection();
	        contexts.addHandler(context);
	        deploymentManager.setContexts( contexts );
	        // deploymentManager.setUseStandardBindings(false);
	        
	        
	        WebAppProvider wap = new WebAppProvider();
	        wap.setScanInterval(5);
	        wap.setDefaultsDescriptor("WEB-INF/web.xml");
	        wap.setMonitoredDirectories( Arrays.asList(new String[]{
	        	"WEB-INF/classes"	
	        }));
	        wap.setDeploymentManager(deploymentManager);
	        wap.setConfigurationClasses( new String[] {
	        	AnnotationConfiguration.class.getName()	
	        });
	                
	        deploymentManager.addAppProvider(wap);
	        
		server.addBean( deploymentManager );
	}
	
	public static void main(String[] args) throws Exception {
		final Server server = new Server(8080);
		
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
        context.setResourceBase( "www" );
        
        // WebAppContext context = new WebAppContext();
        context.setAttribute("javax.servlet.context.tempdir", new File("tmp"));
        // context.setDescriptor("WEB-INF/web.xml");
        
        // that classloader is requres to set JSP classpath. Without it you will just get NPE
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        
        context.addServlet(jspServletHolder, "*.jsp");
        context.addServlet(mvcServletHolder, "/");
        
        /* /
        server.setConfigurations( new Configuration[] {
        		new AnnotationConfiguration()
        });
        /* */
        
        
        // context.addServlet(JqueryServlet.class, "/ext/jquery/js/jquery.js");
        
       
        
        // configureHotDeployement( server, context );
        server.setHandler(context);
        
        
		server.start();
		server.join();
	}

}
