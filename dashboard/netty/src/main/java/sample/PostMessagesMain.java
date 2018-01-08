package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import sample.message.Message;

import java.time.Duration;
import java.util.Date;

/**
 * @author Rob Winch
 */
public class PostMessagesMain {
	private static Logger logger = LoggerFactory.getLogger(PostMessagesMain.class);

	public static void main(String[] args) {
		WebClient rest = WebClient.create("http://localhost:8080");

		Flux<Message> messages = Flux.interval(Duration.ofSeconds(2))
				.map(i -> new Message(i, "Hello " + new Date()));

		rest.post()
				.uri("/messages")
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(messages, Message.class)
				.retrieve()
				.bodyToFlux(Message.class)
				.doOnNext(message -> logger.debug("Posted: " + message))
				.blockLast();
	}
}
