package fr.eservice;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ext.company.elevator.ControlPanel.Button;
import ext.company.elevator.ControlPanel.ControlPanelListener;
import fr.eservice.common.ElevatorListener;


public class AppElevator implements ElevatorListener, ControlPanelListener {

	
	public void start() {
		
	}
	
	
	public void callElevator( int atFloor ) {
		
	}


	@Override
	public void doorOpened() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void doorClosed() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void atFloor(int floor) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void buttonPushed(Button b) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void lightsChanged() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void doorUpdatePosition(double x) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void elevatorUpdatePosition(double x) {
		// TODO Auto-generated method stub
		
	}


	public static void main(String[] args) {
		ApplicationContext context =  new AnnotationConfigApplicationContext(AppElevator.class);

		AppElevator application = context.getBean(AppElevator.class);
		application.start();
	}	

}
