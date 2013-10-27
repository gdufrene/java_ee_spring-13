package fr.eservice.common;

public abstract class DisplayPanel {

	public enum Going {
		DOWN,
		UP
	}
	
	public enum Level {
		LEVEL_1("1st"),
		LEVEL_2("2nd"),
		LEVEL_3("3rd"),
		LEVEL_4("4th"),
		LEVEL_5("5th");
		
		public String name;
		
		Level(String name ) {
			this.name = name;
		}
	}
	
	public void setCurrentLevel( Level level ) {
		
	}
	
	public void setGoing( Going going ) {
		
	}
	
	
}
