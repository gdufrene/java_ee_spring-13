package elevator;



import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.eservice.ElevatorSoftware;
import fr.eservice.common.Elevator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@ComponentScan({"fr.eservice"})
public class TestElevatorSoftware {
	
	private ElevatorSoftware software;
	
	private static Elevator elevator;
	
	@Autowired
	private ApplicationContext context;
	
	@Configuration
	static class Config {
		@Bean
		Elevator getElevator() {
			System.out.println("get Elevator !!!");
			return elevator;
		}
		@Bean
		ElevatorSoftware software() {
			return new ElevatorSoftware();
		}
	}
	
	
	@Test
	public void testCallFloor() {
		elevator = createMock( Elevator.class );
		expect(elevator.currentFloorLevel())
			.andReturn(1);
		elevator.closeDoors();
		elevator.up();
		elevator.up();
		elevator.up();
		elevator.up();
		elevator.stop();
		elevator.openDoors();
		replay( elevator );
		
		software = context.getBean( ElevatorSoftware.class );
		software.callAt(5);
		
		verify( elevator );
	}
	
	@Test
	public void testWantGo() {
		elevator = createMock( Elevator.class );
		expect(elevator.currentFloorLevel()).andReturn(3);
		elevator.closeDoors();
		elevator.down();
		elevator.down();
		elevator.stop();
		elevator.openDoors();
		replay( elevator );
		
		software = context.getBean( ElevatorSoftware.class );
		software.wantGo(1);
		
		verify( elevator );
	}

	

}
