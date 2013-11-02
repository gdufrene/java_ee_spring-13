package fr.eservice.common;

public abstract class DisplayPanel {

	public enum Going {
		DOWN,
		UP,
		NONE
	}
	
	public enum Level {
		LEVEL_1("RdC"),
		LEVEL_2("1er"),
		LEVEL_3("2e"),
		LEVEL_4("3e"),
		LEVEL_5("4e");
		
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
