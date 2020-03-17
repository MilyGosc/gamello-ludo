package gg.gamello.ludo.core.application.dto;

import gg.gamello.ludo.core.domain.Game;

public class GameDtoAssembler {

	public static GameDto convertDefault(Game game) {
		GameDto gameDto = new GameDto();
		gameDto.setId(game.getId());
		gameDto.setBoard(BoardDtoAssembler.convertDefault(game.getBoard()));
		gameDto.setState(game.getState());
		return gameDto;
	}
}
