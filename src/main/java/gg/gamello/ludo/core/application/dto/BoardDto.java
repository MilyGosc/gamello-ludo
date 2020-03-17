package gg.gamello.ludo.core.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BoardDto {

	private List<PlayerDto> players;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UUID currentPlayer;
}
