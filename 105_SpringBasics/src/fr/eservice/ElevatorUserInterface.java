package fr.eservice;

public interface ElevatorUserInterface {
	
	/**
	 * User has pushed a button inside the cabin to go to a floor.
	 * @param level floor requested to go.
	 */
	public void wantGo( int level );
	
	/**
	 * Someone, at a floor, call the elevator
	 * @param level floor requested call.
	 */
	public void callAt( int level );
	
	/**
	 * toggle the elevator alarm. 
	 */
	public void alarm();
	
	
	/**
	 * toggle start/stop state.
	 * When stopped, the elevetor should move at all.
	 * Doors or vetical movement is interrupted.
	 */
	public void stop();
	
	/**
	 * User request doors to stay opened.
	 */
	public void keepOpen();
	
}
