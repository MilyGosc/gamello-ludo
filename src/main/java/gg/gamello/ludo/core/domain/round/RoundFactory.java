package gg.gamello.ludo.core.domain.round;

import gg.gamello.ludo.core.application.command.CreateGameCommand;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class RoundFactory {

	public Round create(CreateGameCommand command) {
		Round round = new Round();
		round.setRegular(new LinkedList<>(command.getPlayersIds()));
		return round;
	}
}
