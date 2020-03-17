package gg.gamello.ludo.core.domain.board;

import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.pawn.Pawn;
import gg.gamello.ludo.core.domain.pawn.Position;
import gg.gamello.ludo.core.domain.player.Player;
import gg.gamello.ludo.core.domain.player.Slot;

import java.util.List;

public interface Movement {

	Position move(Position position, Slot slot, int lastResult);
	List<Pawn> capture(Game game, List<Player> players, Position position);
}
