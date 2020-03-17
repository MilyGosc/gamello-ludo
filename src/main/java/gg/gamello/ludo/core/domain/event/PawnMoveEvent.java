package gg.gamello.ludo.core.domain.event;

import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.pawn.Position;
import lombok.Getter;

import java.util.UUID;

public class PawnMoveEvent extends GameEvent {

	@Getter
	private UUID playerId;

	@Getter
	private UUID pawnId;

	@Getter
	private Position position;

	public PawnMoveEvent(Game game, UUID playerId, UUID pawnId, Position position) {
		super(game);
		this.playerId = playerId;
		this.pawnId = pawnId;
		this.position = position;
	}
}
