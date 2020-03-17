package gg.gamello.ludo.infrastructure.exception;

public class AggregateNotFoundException extends Exception {

	private final String id;

	public AggregateNotFoundException(String id, String message) {
		super(message);
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
