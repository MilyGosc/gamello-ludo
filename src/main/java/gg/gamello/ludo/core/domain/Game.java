package gg.gamello.ludo.core.domain;

import gg.gamello.ludo.core.domain.board.Board;
import gg.gamello.ludo.core.domain.event.GameFinishEvent;
import gg.gamello.ludo.core.domain.event.GameStartEvent;
import gg.gamello.ludo.core.infrastructure.exception.GameOperationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.redis.core.RedisHash;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Domain Aggregate Root
 */
@Slf4j
@RedisHash
@Getter
@NoArgsConstructor
public class Game extends AbstractAggregateRoot<Game> {

	public enum State {
		AWAITING_PLAYERS, IN_PROGRESS, FINISHED
	}

	private UUID id;

	private Board board;

	private State state;

	public Game(Board board) {
		this.board = board;
		this.state = State.AWAITING_PLAYERS;
	}

	public void playerJoin(UUID playerId) throws GameOperationException {
		board.playerJoin(this, playerId);
		start();
	}

	public void playerLeave(UUID playerId) throws GameOperationException {
		board.playerLeave(this, playerId);
	}

	public void diceRoll(UUID playerId) throws GameOperationException {
		if (!state.equals(State.IN_PROGRESS))
			throw new GameOperationException("game has not started yet");
		board.diceRoll(this, playerId);
	}

	public void pawnSelect(UUID playerId, UUID pawnId) throws GameOperationException {
		if (!state.equals(State.IN_PROGRESS))
			throw new GameOperationException("game has not started yet");
		board.pawnMove(this, playerId, pawnId);
	}

	public void start() throws GameOperationException {
		if (!state.equals(State.AWAITING_PLAYERS) || !board.isReady())
			return;
		state = State.IN_PROGRESS;
		registerEvent(new GameStartEvent(this));
		board.getRound().next(this);
	}

	public void finish(LinkedList<UUID> result) {
		state = State.FINISHED;
		registerEvent(new GameFinishEvent(this, result));
	}

	/**
	 * Super class method with overwritten accessor
	 */
	public <T> T registerEvent(T event) {
		return super.registerEvent(event);
	}
}