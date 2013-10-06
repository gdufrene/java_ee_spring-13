package todo;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.eservice.todo.Task;

public class TaskTest {

	@Test
	public void mayBeConstructed_withoutParams() {
		new Task();
	}
	
	@Test
	public void hasCurrentDateSet() {
		Task t = new Task();
		long current = t.getAddedDate();
		assertTrue( "A date must be set when created", current > 0 );
		long now = System.currentTimeMillis() / 1000L;
		assertTrue( "date is always in a near past", current <= now );
		assertTrue( "date should be set at least 2 sec before now", now - current < 2L );
	}
	
	@Test
	public void hasUniqueReference() {
		Task t = new Task();
		int id = t.getRefencene();
		assertTrue( "Task must have a positive reference", id > 0 );
		Task t2 = new Task();
		assertNotSame("Task must have a unique reference", id, t2.getRefencene());
	}
	
	@Test
	public void mayBeCreated() throws Exception {
		// We could expect that this test will be executed before Xmas 2032 ;-)
		Task t = Task.create("Implement create method", "You should add some java code in Task.java", 1987600000L);
		
		assertEquals("Implement create method", t.getTitle());
		assertEquals("You should add some java code in Task.java", t.getDescription());
		assertEquals(1987600000L, t.getTargetDate());
		assertEquals(false, t.hasBeenCompleted());
	}
	
	@Test
	public void mayBeCreated_withoutTargetDate() throws Exception {
		Task t = Task.create("handle null targetDate", "We could consider that a task may not have target Date", null);
		assertEquals("a null target date should be equivalent to 0", 0L, t.getTargetDate());
	}

}
