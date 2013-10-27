package ext.company.elevator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eservice.common.Elevator;
import fr.eservice.common.ElevatorListener;


@Component 
public class StupidElevator implements Elevator, Runnable {
	
	
	private final double verticalSpeed = 1 / 2.5;
	private final double doorSpeed = 1 / 2.0;
	private final int maxLevel = 5;
	
	
	private int level = 1;
	
	enum Movement {
		GOING_DOWN,
		STOPPED,
		GOING_UP
	}
	
	private Movement movement = Movement.STOPPED;
	
	enum DoorTransition {
		WILL_OPEN,
		OPEN,
		WILL_CLOSE,
		CLOSE
	}
	
	private DoorTransition doors = DoorTransition.CLOSE;
	
	boolean isOn = false;
	
	@Autowired
	ElevatorListener listener;
	
	public void run() {
		Double xPosition = new Double(level);
		Double doorPosition = 0d;
		System.out.println("Elevator is now ON.");
		isOn = true;
		long lastTime = System.currentTimeMillis();
		long currentTime = 0L;
		long diff = 0L;
		try {
			while( isOn ) {
				Thread.sleep(100);
				currentTime = System.currentTimeMillis();
				diff = currentTime - lastTime;
				
				if ( movement == Movement.GOING_UP ) {
					xPosition += verticalSpeed * diff / 1000;
				}
				if ( movement == Movement.GOING_UP ) {
					xPosition -= verticalSpeed * diff / 1000;
				}
				if ( xPosition.intValue() != level ) {
					xPosition = new Double(level);
					listener.atFloor(level);
				}
				
				if ( doors == DoorTransition.WILL_OPEN ) {
					doorPosition += doorSpeed * diff / 1000;
				}
				if ( doors == DoorTransition.WILL_CLOSE ) {
					doorPosition -= doorSpeed * diff / 1000;
				}
				if ( doorPosition.intValue() > 1 ) {
					doorPosition = 1.0;
					doors = DoorTransition.OPEN;
					listener.doorOpened();
				}
				if ( doorPosition.intValue() < 0 ) {
					doorPosition = 0.0;
					doors = DoorTransition.CLOSE;
					listener.doorClosed();
				}
				
				lastTime = currentTime;
			}
			System.out.println("Elevator is now OFF.");
		} catch (InterruptedException e) {
			System.err.println("ELEVATOR THREAD INTERUPTED");
			e.printStackTrace();
		}
	}
	
	public void start() {
		if ( isOn ) return;
		new Thread( this ).start();
	}
	
	public void stop() {
		isOn = false;
	}

	@Override
	public void up() {
		if ( movement == Movement.GOING_DOWN ) {
			throw new Error( "Elevator broken. you asked going up whereas it's going down !" );
		}
		if ( level >= maxLevel ) {
			throw new Error( "Elevator broken. you tried to go higher than the roof !" );
		}
		movement = Movement.GOING_UP;
	}

	@Override
	public void down() {
		if ( movement == Movement.GOING_UP ) {
			throw new Error( "Elevator broken. you asked going up whereas it's going up !" );
		}
		if ( level <= 1 ) {
			throw new Error( "Elevator broken. you tried to go lower than the ground !" );
		}
		movement = Movement.GOING_DOWN;
	}

	@Override
	public void openDoors() {
		if ( movement != Movement.STOPPED ) {
			throw new Error( "Elevator broken. you tried to openDoors where as the elevator is moving !" );
		}
		if ( doors == DoorTransition.OPEN ) return;
		doors = DoorTransition.WILL_OPEN;
	}

	@Override
	public void closeDoors() {
		if ( movement != Movement.STOPPED ) {
			throw new Error( "Elevator broken. you tried to openDoors where as the elevator is moving !" );
		}
		if ( doors == DoorTransition.OPEN ) return;
		doors = DoorTransition.WILL_CLOSE;
	}

	@Override
	public int currentFloorLevel() {
		return level;
	}

	
}
