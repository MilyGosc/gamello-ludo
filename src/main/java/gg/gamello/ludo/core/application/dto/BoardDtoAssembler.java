package gg.gamello.ludo.core.application.dto;

import gg.gamello.ludo.core.domain.board.Board;

import java.util.stream.Collectors;

public class BoardDtoAssembler {

	public static BoardDto convertDefault(Board board) {
		BoardDto boardDto = new BoardDto();
		boardDto.setPlayers(board.getPlayers().stream()
				.map(PlayerDtoAssembler::convertDefault)
				.collect(Collectors.toList()));
		boardDto.setCurrentPlayer(board.getRound().getCurrentPlayer());
		return boardDto;
	}
}
