package sample.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sample.message.Message;
import sample.user.User;

import java.util.concurrent.CompletableFuture;

/**
 * @author Rob Winch
 */
@RestController
public class DashboardController {
	final WebClient rest;

	public DashboardController(WebClient rest) {
		this.rest = rest;
	}

	@GetMapping("/dashboard/{id}")
	public Mono<Dashboard> api(@PathVariable Long id) throws Exception {
		return rest.get()
				.uri("/users/{id}", id)
				.exchange()
				.flatMap(response -> response.bodyToMono(User.class))
				.flatMap(user ->
					rest.get()
							.uri("/messages/{username}", user.getName())
							.exchange()
							.flatMap(r -> r.bodyToMono(Message.class))
							.map(m -> new Dashboard(user, m))
				);
	}
}
