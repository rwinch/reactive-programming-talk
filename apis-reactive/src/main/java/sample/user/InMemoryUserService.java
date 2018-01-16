package sample.user;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rob Winch
 */
public class InMemoryUserService {
	private Map<Long,User> usernameToUser = new HashMap<>();

	public InMemoryUserService(User... users) {
		for(User user : users) {
			this.usernameToUser.put(user.getId(), user);
		}
	}

	Mono<User> findById(Long username) {
		return Mono.justOrEmpty(this.usernameToUser.get(username));
	}
}
