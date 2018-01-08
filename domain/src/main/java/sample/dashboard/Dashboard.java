package sample.dashboard;

import sample.message.Message;
import sample.user.User;

/**
 * @author Rob Winch
 */
public class Dashboard {
	final User user;
	final Message message;

	public Dashboard(User user, Message message) {
		this.user = user;
		this.message = message;
	}

	public Message getMessage() {
		return this.message;
	}

	public User getUser() {
		return this.user;
	}
}
