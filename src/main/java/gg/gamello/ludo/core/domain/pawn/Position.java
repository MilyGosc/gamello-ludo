package gg.gamello.ludo.core.domain.pawn;

import lombok.Data;

@Data
public class Position {

	private Location location;

	private int square;

	public Position(Location location, int square) {
		this.location = location;
		this.square = square;
	}

	public enum Location {
		HOME,
		STANDARD,
		FINISH
	}
}
