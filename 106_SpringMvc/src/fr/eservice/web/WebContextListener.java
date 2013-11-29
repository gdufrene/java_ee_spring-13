package fr.eservice.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("HELLO WORLD !!!!!");
	}

}
