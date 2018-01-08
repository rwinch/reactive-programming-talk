package sample.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Rob Winch
 */
@RestController
public class MessageController {
	long sleepTime = TimeUnit.SECONDS.toMillis(1);

	@GetMapping("/messages/{name}")
	public Message messages(@PathVariable String name) throws Exception {
		Thread.sleep(this.sleepTime);
		return new Message("Hello " + name);
	}
}
