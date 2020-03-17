package gg.gamello.ludo.gamemode.application.dto;

import gg.gamello.ludo.gamemode.domain.GameMode;
import org.springframework.stereotype.Component;

@Component
public class GameModeDtoAssembler {

	public GameModeDto convertDefault(GameMode gameMode) {
		var dto = new GameModeDto();
		dto.setName(gameMode.getName());
		return dto;
	}

	public GameModeDetailsDto convertDetails(GameMode gameMode) {
		GameModeDetailsDto dto = new GameModeDetailsDto();
		dto.setName(gameMode.getName());
		dto.setSlots(gameMode.getSlots());
		dto.setSquares(gameMode.getSquares());
		dto.setPawns(gameMode.getPawns());
		return dto;
	}
}
