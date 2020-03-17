package gg.gamello.ludo.core.domain.board;

import gg.gamello.ludo.core.domain.board.movement.ClassicMovement;
import gg.gamello.ludo.gamemode.application.GameModeApplicationService;
import gg.gamello.ludo.gamemode.application.dto.GameModeDetailsDto;
import gg.gamello.ludo.infrastructure.exception.AggregateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovementFactory {

	@Autowired
	private GameModeApplicationService gameModeApplicationService;

	public Movement create(String gameModeName) throws AggregateNotFoundException {
		GameModeDetailsDto gameMode = gameModeApplicationService.getDetails(gameModeName);
		switch (gameMode.getName()) {
			case "classic":
				return new ClassicMovement(gameMode);
			default:
				throw new IllegalArgumentException("movement does not exists for game mode " + gameMode.getName());
		}
	}
}
