package fr.eservice.common;

public interface Elevator {
	
	/**
	 * Ask the elevator to go UP until next floor.
	 * The elevator MUST be STOPPED before moving up.
	 * If the elevator is already going up, the call is ignored.
	 */
	public void up();
	
	
	/**
	 * Ask the elevator to go DOWN until next floor.
	 * The elevator MUST be STOPPED before moving down.
	 * If the elevator is already going down, the call is ignored.
	 */
	public void down();
	
	
	/**
	 * Start the elevator. (continue previous action)
	 */
	public void start();
	
	
	/**
	 * Stop the elevator at the current location.
	 */
	public void stop();
	
	
	/**
	 * Try to open doors, the elevator must be stopped at a floor !
	 * keep door open for a moment if its already opened.
	 */
	public void openDoors();
	
	
	/**
	 * Close the doors, ignoring call if doors are closed. 
	 */
	public void closeDoors();
	
	
	/**
	 * Return the last floor passed by the elevator.
	 * Beware, the elevator may going up between floor 1 and 2 : this
	 * operation returns floor 1 until floor 2 is completely reach.
	 *   
	 * @return current floor passed
	 */
	public int currentFloorLevel();

}