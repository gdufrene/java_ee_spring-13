package ext.company.elevator;

import java.util.List;

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
	List<ElevatorListener> listeners;
	
	Double xPosition = new Double(level);
	Double doorPosition = 0d;
	
	public void run() {
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
				int compareLevel = level;
				if ( movement == Movement.GOING_UP ) {
					xPosition += verticalSpeed * diff / 1000;
				}
				if ( movement == Movement.GOING_DOWN ) {
					xPosition -= verticalSpeed * diff / 1000;
					compareLevel--;
				}
				if ( movement != Movement.STOPPED ) {
					for ( ElevatorListener listener : listeners ) listener.elevatorUpdatePosition(xPosition);
				}
				if ( xPosition.intValue() != compareLevel ) {
					System.out.println("New position reach : " + xPosition);
					level = (int) Math.round( xPosition );
					xPosition = level / 1.0;
					movement = Movement.STOPPED;
					for ( ElevatorListener listener : listeners ) listener.atFloor(level);
				}

				
				if ( doors == DoorTransition.WILL_OPEN ) {
					doorPosition += doorSpeed * diff / 1000;
					
					if ( doorPosition >= 1.0 ) {
						doorPosition = 1.0;
						doors = DoorTransition.OPEN;
						for ( ElevatorListener listener : listeners ) listener.doorOpened();
					}
					for ( ElevatorListener listener : listeners ) listener.doorUpdatePosition(doorPosition);
				}
				if ( doors == DoorTransition.WILL_CLOSE ) {
					doorPosition -= doorSpeed * diff / 1000;
					
					if ( doorPosition <= 0.0 ) {
						doorPosition = 0.0;
						doors = DoorTransition.CLOSE;
						for ( ElevatorListener listener : listeners ) listener.doorClosed();
					}
					for ( ElevatorListener listener : listeners ) listener.doorUpdatePosition(doorPosition);
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
		System.out.println("Elevator:start()");
		if ( isOn ) return;
		new Thread( this ).start();
	}
	
	public void stop() {
		System.out.println("Elevator:stop()");
		isOn = false;
		System.out.println("Elevator is OFF");
	}

	@Override
	public void up() {
		System.out.println("Elevator:up()");
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
		System.out.println("Elevator:down()");
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
		System.out.println("Elevator:openDoors()");
		if ( movement != Movement.STOPPED ) {
			throw new Error( "Elevator broken. you tried to openDoors where as the elevator is moving !" );
		}
		//if ( doors == DoorTransition.OPEN ) return;
		doors = DoorTransition.WILL_OPEN;
	}

	@Override
	public void closeDoors() {
		System.out.println("Elevator:closeDoors()");
		if ( movement != Movement.STOPPED ) {
			throw new Error( "Elevator broken. you tried to openDoors where as the elevator is moving !" );
		}
		//if ( doors == DoorTransition.CLOSE ) return;
		doors = DoorTransition.WILL_CLOSE;
	}

	@Override
	public int currentFloorLevel() {
		return level;
	}

	
}
