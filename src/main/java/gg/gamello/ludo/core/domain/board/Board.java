package gg.gamello.ludo.core.domain.board;

import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.pawn.Pawn;
import gg.gamello.ludo.core.domain.pawn.Position;
import gg.gamello.ludo.core.domain.player.Player;
import gg.gamello.ludo.core.domain.round.Round;
import gg.gamello.ludo.core.infrastructure.exception.GameOperationException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Setter(AccessLevel.PACKAGE)
public class Board {

	private List<Player> players;

	private Movement movement;

	private Round round;

	/**
	 * Order of completing game by players.
	 */
	private LinkedList<UUID> result = new LinkedList<>();

	public void playerJoin(Game game, UUID playerId) throws GameOperationException {
		getPlayer(playerId).join(game);
	}

	public void playerLeave(Game game, UUID playerId) throws GameOperationException {
		getPlayer(playerId).leave(game);
	}

	public void diceRoll(Game game, UUID playerId) throws GameOperationException {
		if (!round.isCurrentPlayer(playerId))
			throw new GameOperationException("round belongs to another player");
		getPlayer(playerId).applyDiceRoll(game);
		round.next(game);
	}

	public void pawnMove(Game game, UUID playerId, UUID pawnId) throws GameOperationException {
		if (!round.isCurrentPlayer(playerId))
			throw new GameOperationException("round belongs to another player");
		getPlayer(playerId).pawnMove(game, pawnId);
		round.next(game);
	}

	public void capturePawns(Game game, UUID excludedPlayerId, Position position) {
		List<Player> enemies = players.stream()
				.filter(player -> !player.getId().equals(excludedPlayerId))
				.collect(Collectors.toList());
		List<Pawn> capturedPawns = movement.capture(game, enemies, position);
		if (!capturedPawns.isEmpty())
			round.addBonus(excludedPlayerId);
	}

	public void playerFinished(Game game, UUID playerId) {
		result.add(playerId);
		round.removePlayer(playerId);
		if (result.size() == players.size())
			game.finish(result);
	}

	public Player getPlayer(UUID playerId) throws GameOperationException {
		return players.stream()
				.filter(player -> player.getId().equals(playerId))
				.findFirst().orElseThrow(() -> new GameOperationException("player does not exists"));
	}

	public boolean containsPlayer(UUID playerId) {
		return players.stream().anyMatch(player -> player.getId().equals(playerId));
	}

	public boolean isReady() {
		return players.stream().allMatch(Player::hasConnected);
	}
}
