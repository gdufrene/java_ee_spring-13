package elevator;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.eservice.ElevatorSoftware;
import fr.eservice.common.DisplayPanel;
import fr.eservice.common.Elevator;
import fr.eservice.common.ElevatorListener;


@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration( classes={TestElevatorSoftware.class})
public class TestElevatorSoftware {
	
	@Autowired
	private ElevatorSoftware software;
	
	@Autowired
	private Elevator elevator;
	
	private int currentLevel = 1;
	
	@Before
	public void init() {
		currentLevel = 1;
	}
	
	@Bean 
	public DisplayPanel getDisplayPanel() {
		return new ConsolePanel();
	}
	
	@Bean 
	public ElevatorSoftware getElevatorSoftware() {
		return new ElevatorSoftware();
	}
	
	@Bean
	public Elevator getElevatorMock() {
		return new Elevator() {
			boolean doorClosed = false;
			boolean started = false;
			@Autowired
			ElevatorListener listener;
			
			@Override
			public void up() {
				if ( !started ) throw new Error("Elevator not started !");
				if ( !doorClosed ) throw new Error("Doors open !");
				currentLevel++;
				if ( currentLevel > 5 ) throw new Error("Hit the roof");
				listener.atFloor(currentLevel);
			}
			
			@Override
			public void down() {
				if ( !started ) throw new Error("Elevator not started !");
				if ( !doorClosed ) throw new Error("Doors open !");
				currentLevel--;
				if ( currentLevel < 1 ) throw new Error("Hit the ground");
				listener.atFloor(currentLevel);
			}
			
			@Override
			public int currentFloorLevel() {
				return currentLevel;
			}
			
			@Override public void start() { started = true; }
			@Override public void stop() { started = false; }
			@Override public void closeDoors() { 
				doorClosed = true;
				listener.doorClosed(); 
			}
			@Override public void openDoors() {
				doorClosed = false;
				listener.doorOpened(); 
			}
			
		};
	}
	
	
	@Test
	public void testCallFloor() {
		currentLevel = 1;
		software.init();
		software.callAt(5);
		assertEquals(5, elevator.currentFloorLevel());
	}
	
	@Test
	public void testWantGo() {
		currentLevel = 3;
		software.init();
		software.wantGo(1);
		assertEquals(1, elevator.currentFloorLevel());
	}

	

}
