package todo;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.eservice.todo.Task;
import fr.eservice.todo.TodoList;
import fr.eservice.todo.TodoList.ORDER;

public class TodoTest {

	@Test
	public void canCreateEmptyList() {
		TodoList list = new TodoList();
		Task[] tasks = list.getTasks(ORDER.ADDED_DATE);
		assertNotNull(tasks);
		assertEquals(0, tasks.length);
	}
	
	
}
