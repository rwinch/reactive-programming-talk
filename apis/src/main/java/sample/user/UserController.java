package sample.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Rob Winch
 */
@RestController
public class UserController {
	long sleepTime = TimeUnit.SECONDS.toMillis(1);

	private final InMemoryUserService users;

	public UserController(InMemoryUserService users) {
		this.users = users;
	}

	@GetMapping("/users/{id}")
	public User index(@PathVariable Long id)  throws Exception {
		Thread.sleep(this.sleepTime);
		return this.users.findById(id);
	}
}
