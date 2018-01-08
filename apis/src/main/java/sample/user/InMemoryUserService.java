package sample.user;

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

	public User findById(Long username) {
		return this.usernameToUser.get(username);
	}
}
