package sample.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.client.RestOperations;
import sample.message.Message;
import sample.user.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author Rob Winch
 */
@RestController
public class DashboardController {
	final AsyncRestOperations rest;

	public DashboardController(AsyncRestOperations rest) {
		this.rest = rest;
	}

	@GetMapping("/dashboard/{id}")
	public CompletableFuture<Dashboard> api(@PathVariable Long id) throws Exception {
		CompletableFuture<ResponseEntity<User>> user = this.rest.getForEntity("/users/{id}", User.class, id).completable();
		return user.thenCompose(userResponseEntity -> {
			User u = userResponseEntity.getBody();
			return this.rest.getForEntity("/messages/{username}", Message.class, u.getName()).completable()
					.thenApply(messageResponseEntity -> new Dashboard(u, messageResponseEntity.getBody()));
		});
	}
}
