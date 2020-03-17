package gg.gamello.ludo.core.domain.round;

import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.event.RoundNewEvent;
import gg.gamello.ludo.core.infrastructure.exception.GameOperationException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.LinkedList;
import java.util.UUID;

@Data
@Setter(AccessLevel.PACKAGE)
public class Round {

	/**
	 * Regular round circle, performed until all players will be removed.
	 */
	private LinkedList<UUID> regularSequence = new LinkedList<>();

	/**
	 * Bonus rounds, performed before regular rounds.
	 */
	private LinkedList<UUID> bonusSequence = new LinkedList<>();

	private UUID currentPlayer;

	public void next(Game game) throws GameOperationException {
		if (currentPlayer != null || game.getBoard().getPlayer(currentPlayer).hasMove())
			return;
		currentPlayer = poolNext();
		game.getBoard().getPlayer(currentPlayer).nextRound(game);
		game.registerEvent(new RoundNewEvent(game, currentPlayer));
	}

	public void addBonus(UUID playerId) {
		bonusSequence.add(playerId);
	}

	public void removePlayer(UUID playerId) {
		if (currentPlayer.equals(playerId))
			currentPlayer = null;
		regularSequence.remove(playerId);
		bonusSequence.remove(playerId);
	}

	public boolean isCurrentPlayer(UUID playerId) {
		return currentPlayer.equals(playerId);
	}

	private UUID poolNext() {
		if (!bonusSequence.isEmpty())
			return poolBonusRound();
		return poolRegularRound();
	}

	private UUID poolBonusRound() {
		return bonusSequence.poll();
	}

	private UUID poolRegularRound() {
		if (currentPlayer != null)
			regularSequence.add(currentPlayer);
		return regularSequence.poll();
	}
}
