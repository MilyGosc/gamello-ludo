package gg.gamello.ludo.core.domain;

import gg.gamello.ludo.core.application.command.CreateGameCommand;
import gg.gamello.ludo.core.domain.board.BoardFactory;
import gg.gamello.ludo.infrastructure.exception.AggregateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameFactory {

	@Autowired
	private BoardFactory boardFactory;

	public Game create(CreateGameCommand command) throws AggregateNotFoundException {
		return new Game(boardFactory.create(command));
	}
}
