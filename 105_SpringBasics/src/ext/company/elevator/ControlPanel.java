package ext.company.elevator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eservice.ElevatorUserInterface;

@Component
public class ControlPanel {
	
	@Autowired
	private ElevatorUserInterface userControl;
	
	public enum Button {
		KEEP_OPEN("<>"),
		LEVEL_1("RdC"),
		LEVEL_2("1"),
		LEVEL_3("2"),
		LEVEL_4("3"),
		LEVEL_5("4"),
		ALARM("/!\\"),
		STOP("[X]");
		
		public String label;
		private Button(String label) {
			this.label = label;
		}
	}
	
	public void push( Button b ) {
		System.out.println("Control panel push " + b.name());
		if ( userControl == null ) return;
		switch (b) {
		case LEVEL_1:
		case LEVEL_2:
		case LEVEL_3:
		case LEVEL_4:
		case LEVEL_5:
			int level = 0;
			String name = b.name();
			if ( !name.startsWith("LEVEL_") ) return;
			try {
				level = Integer.parseInt( name.substring(6) );
			} catch (NumberFormatException e) {
				return;
			}
			userControl.wantGo(level);
			break;
		case ALARM:
			userControl.alarm();
		default:
		case STOP:
			userControl.stop();
			break;
		}
	}
	
	public void pushCallButton( int level ) {
		userControl.callAt(level);
	}
	

}
