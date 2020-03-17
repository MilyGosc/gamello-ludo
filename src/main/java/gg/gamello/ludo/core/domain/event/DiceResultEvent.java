package gg.gamello.ludo.core.domain.event;

import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.pawn.Pawn;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class DiceResultEvent extends GameEvent {

	@Getter
	private UUID playerId;

	@Getter
	private int result;

	@Getter
	private List<Pawn> movablePawns;

	public DiceResultEvent(Game game, UUID playerId, int result, List<Pawn> movablePawns) {
		super(game);
		this.playerId = playerId;
		this.result = result;
		this.movablePawns = movablePawns;
	}
}
