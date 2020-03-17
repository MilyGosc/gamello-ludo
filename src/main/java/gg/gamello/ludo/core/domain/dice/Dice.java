package gg.gamello.ludo.core.domain.dice;

import lombok.Getter;

import java.util.Random;

@Getter
public class Dice {

	public enum State {
		UNROLLED, ROLLED
	}

	private int result;

	private State state;

	public Dice() {
		this.state = State.ROLLED;
	}

	public int roll() {
		state = State.UNROLLED;
		return result = new Random().nextInt(6) + 1;
	}

	public boolean hasRolled() {
		return state.equals(State.ROLLED);
	}

	public int applyResult() {
		state = State.ROLLED;
		return result;
	}
}
