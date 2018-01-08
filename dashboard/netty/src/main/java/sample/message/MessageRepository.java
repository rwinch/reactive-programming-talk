package sample.message;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * @author Rob Winch
 */
public interface MessageRepository extends ReactiveMongoRepository<Message, Long> {
	@Tailable
	Flux<Message> findMessagesBy();
}
