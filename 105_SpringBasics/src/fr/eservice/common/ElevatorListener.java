package fr.eservice.common;

public interface ElevatorListener {
	
	/**
	 * Doors are now fully opened !
	 */
	public void doorOpened();
	
	/**
	 * Doors are now closed.
	 */
	public void doorClosed();
	
	/**
	 * Elevator has reach a floor
	 * @param floor
	 */
	public void atFloor( int floor );
	
	/**
	 * Doors are currently moving. Give the current position.
	 * @param x doors position between 0 (open) and 1 (close)
	 */
	public void doorUpdatePosition( double x );
	
	/**
	 * The elevator is moving. Give the current poisition.
	 * @param x current level position from 1.0 (1st floor) to 5.0 (last floor)
	 */
	public void elevatorUpdatePosition( double x );
	
	

}
