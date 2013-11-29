package fr.eservice.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInit implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		
		System.out.println("initialize servletContext");
		
		final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(Config.class);
		
        ServletRegistration.Dynamic registration = context.addServlet("dispatcher", new DispatcherServlet(applicationContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
	}
	
}
