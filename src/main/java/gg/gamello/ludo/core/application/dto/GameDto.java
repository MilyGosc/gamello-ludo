package gg.gamello.ludo.core.application.dto;

import gg.gamello.ludo.core.domain.Game;
import lombok.Data;

import java.util.UUID;

@Data
public class GameDto {

	private UUID id;

	private BoardDto board;

	private Game.State state;
}
