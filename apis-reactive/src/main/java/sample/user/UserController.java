package sample.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author Rob Winch
 */
@RestController
public class UserController {
	private Duration delay = Duration.ofSeconds(1);

	private final InMemoryUserService users;

	public UserController(InMemoryUserService users) {
		this.users = users;
	}

	@GetMapping("/users/{id}")
	Mono<User> index(@PathVariable Long id)  throws Exception {
		return this.users.findById(id)
				.delayElement(this.delay);
	}
}
