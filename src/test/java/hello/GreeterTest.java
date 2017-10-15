package hello;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class GreeterTest {

	private static Logger logger = LoggerFactory.getLogger(GreeterTest.class);

	private Greeter greeter = new Greeter();

	@Test
	public void greeterSaysHello() {
		logger.info("test start");
		assertThat(greeter.sayHello(), containsString("Hello"));
		logger.info("test end");
	}

}
