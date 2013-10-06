package todo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TaskTest.class,
	TodoTest.class
})
public class AllTests { }
