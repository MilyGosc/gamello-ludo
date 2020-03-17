package gg.gamello.ludo.core.domain.pawn;

import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.event.PawnMoveEvent;
import gg.gamello.ludo.core.infrastructure.exception.GameOperationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pawn {

	private UUID id;

	private int home;

	private Position position;

	@Setter
	private Position possibleMove;

	public Pawn(UUID id, int home) {
		this(id, home, new Position(Position.Location.HOME, home), null);
	}

	public void applyMove(Game game, UUID playerId) throws GameOperationException {
		if (possibleMove == null)
			throw new GameOperationException("pawn can not be moved");
		position = possibleMove;
		game.registerEvent(new PawnMoveEvent(game, playerId, id, position));
		game.getBoard().capturePawns(game, playerId, position);
	}

	public void home(Game game, UUID playerId) {
		position = new Position(Position.Location.HOME, home);
		game.registerEvent(new PawnMoveEvent(game, playerId, id, position));
	}
}
