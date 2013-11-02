package fr.eservice;

import fr.eservice.common.DisplayPanel;
import fr.eservice.common.DisplayPanel.Level;
import fr.eservice.common.Elevator;
import fr.eservice.common.ElevatorListener;


public class ElevatorSoftware implements ElevatorListener, ElevatorUserInterface {
	
	private Elevator elevator;
	
	private DisplayPanel panel;
	
	public void init() {
		elevator.start();
		started = true;
		panel.setCurrentLevel( Level.values()[elevator.currentFloorLevel()-1] );
	}
	
	@Override
	public void callAt(int level) {
		System.out.println("Software:callAt " + level);
		// TODO 
	}
	
	@Override
	public void wantGo(int level) {
		// TODO 
	}
	
	@Override
	public void doorOpened() {
		System.out.println("Software:doorOpened");
		// TODO
	}
	
	boolean mayKeepOpen = false;

	@Override
	public void doorClosed() {
		System.out.println("Software:doorClosed");
		// TODO
	}

	@Override
	public void atFloor(int floor) {
		System.out.println("At floor ... " + floor);
		// TODO
	}

	@Override
	public void doorUpdatePosition(double x) { }

	@Override
	public void elevatorUpdatePosition(double x) { }
	
	boolean started = false;
	
	@Override
	public void stop() {
		// TODO
	}
	
	@Override
	public void keepOpen() {
		// TODO
	}
	
	@Override
	public void alarm() {
		// TODO
	}
	

}
