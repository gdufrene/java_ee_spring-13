package fr.eservice;

import fr.eservice.common.ElevatorListener;

public class ElevatorSoftware implements ElevatorListener, ElevatorUserInterface {
	
	@Override
	public void callAt(int level) {
		throw new Error("Not yet implemented !");
	}
	
	@Override
	public void wantGo(int level) {
		throw new Error("Not yet implemented !");
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
	public void doorUpdatePosition(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void elevatorUpdatePosition(double x) {
		// TODO Auto-generated method stub
		
	}

}
