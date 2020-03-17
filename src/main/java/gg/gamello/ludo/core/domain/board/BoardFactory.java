package gg.gamello.ludo.core.domain.board;

import gg.gamello.ludo.core.application.command.CreateGameCommand;
import gg.gamello.ludo.core.domain.player.Player;
import gg.gamello.ludo.core.domain.player.PlayerFactory;
import gg.gamello.ludo.core.domain.round.Round;
import gg.gamello.ludo.core.domain.round.RoundFactory;
import gg.gamello.ludo.infrastructure.exception.AggregateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoardFactory {

	@Autowired
	private PlayerFactory playerFactory;

	@Autowired
	private MovementFactory movementFactory;

	@Autowired
	private RoundFactory roundFactory;

	public Board create(CreateGameCommand command) throws AggregateNotFoundException {
		List<Player> players = new ArrayList<>(command.getPlayersIds().size());
		for (int i = 0; i < command.getPlayersIds().size(); i++)
			players.add(playerFactory.create(command.getPlayersIds().get(i), i, command.getGameModeName()));
		Board board = new Board();
		board.setRound(roundFactory.create(command));
		board.setPlayers(players);
		board.setMovement(movementFactory.create(command.getGameModeName()));
		return board;
	}
}
