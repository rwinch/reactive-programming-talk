package sample.message;

/**
 * @author Rob Winch
 */
public class Message {
	private Long id;

	private String text;

	public Message(Long id, String text) {
		this.id = id;
		this.text = text;
	}

	public Message(String text) {
		this.text = text;
	}

	public Message() {}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
