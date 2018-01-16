package sample.message;

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
public class MessageController {
	private Duration delay = Duration.ofSeconds(1);

	@GetMapping("/messages/{name}")
	Mono<Message> messages(@PathVariable String name) throws Exception {
		return Mono.just(new Message("Hello " + name))
				.delayElement(this.delay);
	}
}
