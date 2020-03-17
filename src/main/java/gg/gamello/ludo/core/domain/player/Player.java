package gg.gamello.ludo.core.domain.player;

import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.board.Movement;
import gg.gamello.ludo.core.domain.dice.Dice;
import gg.gamello.ludo.core.domain.event.DiceResultEvent;
import gg.gamello.ludo.core.domain.event.PlayerJoinEvent;
import gg.gamello.ludo.core.domain.pawn.Pawn;
import gg.gamello.ludo.core.domain.pawn.Position;
import gg.gamello.ludo.core.infrastructure.exception.GameOperationException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class Player {

	private UUID id;

	private List<Pawn> pawns;

	private Dice dice;

	private Slot slot;

	private Connection connection;

	private boolean moved;

	public Player(UUID id, List<Pawn> pawns, Dice dice, Slot slot) {
		this.id = id;
		this.pawns = pawns;
		this.dice = dice;
		this.slot = slot;
		this.connection = new Connection();
	}

	public boolean join(Game game) {
		if (!connection.connect())
			return false;
		game.registerEvent(new PlayerJoinEvent(game, id));
		return true;
	}

	public boolean leave(Game game) {
		if (!connection.disconnect())
			return false;
		game.registerEvent(new PlayerJoinEvent(game, id));
		return true;
	}

	private void diceRoll(Game game) {
		dice.roll();
		if (dice.getResult() == 6)
			game.getBoard().getRound().addBonus(id);
	}

	public void applyDiceRoll(Game game) throws GameOperationException {
		if (dice.hasRolled())
			throw new GameOperationException("roll has been already done");
		game.registerEvent(new DiceResultEvent(game, id, dice.applyResult(), getMovablePawns()));
	}

	public void pawnMove(Game game, UUID pawnId) throws GameOperationException {
		if (!dice.hasRolled())
			throw new GameOperationException("roll must be executed before moving pawn");
		getPawn(pawnId).applyMove(game, id);
		moved = true;
		finish(game);
	}

	public void nextRound(Game game) {
		moved = false;
		diceRoll(game);
		Movement movement = game.getBoard().getMovement();
		pawns.forEach(pawn -> pawn.setPossibleMove(movement.move(pawn.getPosition(), slot, dice.getResult())));
	}

	private void finish(Game game) throws GameOperationException {
		boolean finished = pawns.stream()
				.allMatch(pawn -> pawn.getPosition().getLocation().equals(Position.Location.FINISH));
		if (finished)
			game.getBoard().playerFinished(game, id);
	}

	public Pawn getPawn(UUID pawnId) throws GameOperationException {
		return pawns.stream()
				.filter(pawn -> pawn.getId().equals(pawnId))
				.findFirst().orElseThrow(() -> new GameOperationException("pawn does not exists"));
	}

	public List<Pawn> getMovablePawns() {
		return pawns.stream()
				.filter(pawn -> pawn.getPossibleMove() != null)
				.collect(Collectors.toList());
	}

	public boolean hasConnected() {
		return connection.hasConnected();
	}

	public boolean hasMove() {
		return getMovablePawns().size() != 0 && !moved;
	}
}
