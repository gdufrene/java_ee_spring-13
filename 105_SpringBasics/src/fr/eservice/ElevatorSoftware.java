package fr.eservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eservice.common.DisplayPanel;
import fr.eservice.common.DisplayPanel.Going;
import fr.eservice.common.DisplayPanel.Level;
import fr.eservice.common.Elevator;
import fr.eservice.common.ElevatorListener;

@Component
public class ElevatorSoftware implements ElevatorListener, ElevatorUserInterface {
	
	@Autowired
	private Elevator elevator;
	
	@Autowired
	private DisplayPanel panel;
	
	private int targetFloor = -1;
	
	public void init() {
		elevator.start();
		started = true;
		panel.setCurrentLevel( Level.values()[elevator.currentFloorLevel()-1] );
	}
	
	@Override
	public void callAt(int level) {
		System.out.println("Software:callAt " + level);
		if ( level < 1 || level > 5 ) return;
		if ( targetFloor > 0 ) return;
		
		int currentLevel = elevator.currentFloorLevel();
		targetFloor = level;
		if ( level == currentLevel ) {
			elevator.openDoors();
			return;
		}
		
		
		if ( targetFloor > currentLevel ) panel.setGoing(Going.UP);
		else panel.setGoing(Going.DOWN);
		
		elevator.closeDoors();
	}
	
	@Override
	public void wantGo(int level) {
		callAt( level );
	}
	
	@Override
	public void doorOpened() {
		System.out.println("Software:doorOpened");
		mayKeepOpen = true;
		int level = elevator.currentFloorLevel();
		System.out.println("Welcome to floor " + elevator.currentFloorLevel() );
		if ( level != targetFloor ) {
			elevator.closeDoors();
		} else {
			targetFloor = -1;
			panel.setGoing(Going.NONE);
		}
	}
	
	boolean mayKeepOpen = false;

	@Override
	public void doorClosed() {
		System.out.println("Software:doorClosed");
		if ( targetFloor > 0 ) {
			mayKeepOpen = false;
			atFloor( elevator.currentFloorLevel() );
		}
	}

	@Override
	public void atFloor(int floor) {
		System.out.println("At floor ... " + floor);
		if ( targetFloor < 0 || targetFloor == floor ) {
			elevator.openDoors();
			return;
		}
		if (targetFloor > floor ) elevator.up() ;
		else elevator.down();
	}

	@Override
	public void doorUpdatePosition(double x) { }

	@Override
	public void elevatorUpdatePosition(double x) { }
	
	boolean started = false;
	
	@Override
	public void stop() {
		started = !started;
		if ( started ) elevator.start();
		else elevator.stop();
	}
	
	@Override
	public void keepOpen() {
		if ( mayKeepOpen ) {
			elevator.openDoors();
			return;
		}
	}
	
	@Override
	public void alarm() {
		// TODO Auto-generated method stub
		
	}
	

}
