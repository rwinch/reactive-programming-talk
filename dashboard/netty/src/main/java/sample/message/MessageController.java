package sample.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Rob Winch
 */
@RestController
public class MessageController {
	final MessageRepository messages;

	public MessageController(MessageRepository messages) {
		this.messages = messages;
	}

	@GetMapping(path = "/messages", produces = "application/stream+json")
	public Flux<Message> streamAll() {
		return this.messages.findMessagesBy().log();
	}

	@PostMapping(path="/messages", consumes = "application/stream+json")
	@ResponseStatus(HttpStatus.CREATED)
	public Flux<Message> save(@RequestBody Flux<Message> messages) {
		return this.messages.insert(messages);
	}
}
