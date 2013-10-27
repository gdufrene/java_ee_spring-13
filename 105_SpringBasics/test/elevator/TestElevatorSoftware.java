package elevator;



import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.eservice.ElevatorSoftware;
import fr.eservice.common.Elevator;
import fr.eservice.common.ElevatorListener;


@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration( classes={TestElevatorSoftware.class})
@ComponentScan({"fr.eservice"})
public class TestElevatorSoftware {
	
	@Autowired
	private ElevatorSoftware software;
	
	@Autowired
	private Elevator elevator;
	
	private int currentLevel;
	
	@Bean
	public Elevator getElevator() {
		return new Elevator() {
			
			ElevatorListener listener = software;
			
			@Override
			public void up() {
				currentLevel++;
				if ( currentLevel > 5 ) throw new Error("Hit the roof");
				listener.atFloor(currentLevel);
			}
			
			@Override
			public void start() {
				
			}
			
			@Override
			public void stop() {

			}
			
			@Override
			public void openDoors() {
				listener.doorOpened();
			}
			
			@Override
			public void down() {
				currentLevel--;
				if ( currentLevel < 1 ) throw new Error("Hit the ground");
				listener.atFloor(currentLevel);
			}
			
			@Override
			public int currentFloorLevel() {
				return currentLevel;
			}
			
			@Override
			public void closeDoors() {
				listener.doorClosed();
			}
		};
	}
	
	
	@Test
	public void testCallFloor() {

		currentLevel = 1;
		software.callAt(5);
		assertEquals(5, elevator.currentFloorLevel());
		
	}
	
	@Test
	public void testWantGo() {
		currentLevel = 3;
		software.wantGo(1);
		assertEquals(1, elevator.currentFloorLevel());
	}

	

}
