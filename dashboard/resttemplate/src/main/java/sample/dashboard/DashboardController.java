package sample.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import sample.message.Message;
import sample.user.User;

/**
 * @author Rob Winch
 */
@RestController
public class DashboardController {
	final RestOperations rest;

	public DashboardController(RestOperations rest) {
		this.rest = rest;
	}

	@GetMapping("/dashboard/{id}")
	Dashboard api(@PathVariable Long id) {
		User user = this.rest.getForObject("/users/{id}", User.class, id);
		Message message = this.rest.getForObject("/messages/{username}", Message.class, id);
		return new Dashboard(user, message);
	}
}
