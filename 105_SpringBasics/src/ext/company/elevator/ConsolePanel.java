package ext.company.elevator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.eservice.common.DisplayPanel;

@Component
@Qualifier("console")
public class ConsolePanel extends DisplayPanel {

	@Override
	public void setCurrentLevel(Level level) {
		System.out.println("You are now on" + level.name + " floor.");
	}
	
	@Override
	public void setGoing(Going going) {
		System.out.println("The elevator is now going " + going.name() );
	}
	
}
