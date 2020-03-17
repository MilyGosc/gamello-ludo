package gg.gamello.ludo.core.domain.board.movement;

import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.board.Movement;
import gg.gamello.ludo.core.domain.pawn.Pawn;
import gg.gamello.ludo.core.domain.pawn.Position;
import gg.gamello.ludo.core.domain.player.Player;
import gg.gamello.ludo.core.domain.player.Slot;
import gg.gamello.ludo.gamemode.application.dto.GameModeDetailsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ClassicMovement implements Movement {

	private int squares;

	public ClassicMovement(GameModeDetailsDto gameMode) {
		this.squares = gameMode.getSquares();
	}

	@Override
	public Position move(Position position, Slot slot, int lastResult) {
		switch (position.getLocation()) {
			case HOME:
				if (lastResult == 6)
					return new Position(Position.Location.STANDARD, slot.getStart());
				break;
			case STANDARD:
				if (slot.getFinish() >= position.getSquare() && slot.getFinish() < position.getSquare() + lastResult) {
					var finish = position.getSquare() + lastResult - slot.getFinish() - 1;
					if (finish <= 3)
						return new Position(Position.Location.FINISH, finish);
					break;
				}
				return new Position(Position.Location.STANDARD, (position.getSquare() + lastResult) % squares);
			case FINISH:
				if (position.getSquare() + lastResult <= 3)
					return new Position(Position.Location.FINISH, position.getSquare() + lastResult);
				break;
		}
		return null;
	}

	public List<Pawn> capture(Game game, List<Player> players, Position position) {
		List<Pawn> capturedPawns = new ArrayList<>();
		if (position.getLocation().equals(Position.Location.FINISH))
			return capturedPawns;
		for (Player player : players) {
			for (Pawn pawn : player.getPawns()) {
				if (pawn.getPosition().equals(position)) {
					pawn.home(game, player.getId());
					capturedPawns.add(pawn);
				}
			}
		}
		return capturedPawns;
	}
}
