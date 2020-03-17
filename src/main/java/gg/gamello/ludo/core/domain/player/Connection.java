package gg.gamello.ludo.core.domain.player;

public class Connection {

	public enum State {
		CONNECTED, DISCONNECTED
	}

	private State state;

	private int connections;

	private int disconnections;

	public Connection() {
		this.state = State.DISCONNECTED;
	}

	public boolean isConnected() {
		return state.equals(State.CONNECTED);
	}

	public boolean hasConnected() {
		return connections > 0;
	}

	public boolean hasDisconnected() {
		return disconnections > 0;
	}

	public boolean connect() {
		if (state.equals(State.CONNECTED))
			return false;
		connections++;
		this.state = State.CONNECTED;
		return true;
	}

	public boolean disconnect() {
		if (state.equals(State.DISCONNECTED))
			return false;
		disconnections++;
		this.state = State.DISCONNECTED;
		return true;
	}
}
