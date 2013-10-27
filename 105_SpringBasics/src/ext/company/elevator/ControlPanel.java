package ext.company.elevator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlPanel {
	
	public interface ControlPanelListener {
		public void buttonPushed( Button b );
		public void lightsChanged( );
	}
	
	@Autowired
	private ControlPanelListener listener;
	
	public enum Button {
		KEEP_OPEN,
		LEVEL_1,
		LEVEL_2,
		LEVEL_3,
		LEVEL_4,
		LEVEL_5,
		ALARM,
		STOP
	}
	
	Set<Button> lightsOn = new HashSet<ControlPanel.Button>(Button.values().length);
	
	
	public void push( Button b ) {
		System.out.println("Control panel push " + b.name());
		listener.buttonPushed(b);
	}
	
	public void setLight( Button b, boolean on ) {
		boolean changed = ( on ? lightsOn.add(b) : lightsOn.remove(b) );
		if ( changed ) {
			listener.lightsChanged();
		}
	}
	
	public Button[] getLightsOn( ) {
		return lightsOn.toArray( new Button[]{} );
	}

}
