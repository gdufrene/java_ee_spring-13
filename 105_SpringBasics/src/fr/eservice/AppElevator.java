package fr.eservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"fr.eservice", "ext.company.elevator", "another.company.elevator"})
public class AppElevator {
	
	
	@Autowired
	ElevatorSoftware software;
	
	@Autowired
	ApplicationContext context;

	
	public void start() {
		software.init();
		System.out.println("Application started.");
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(AppElevator.class) ;

		AppElevator application = context.getBean(AppElevator.class);
		application.start();
	}	

}
