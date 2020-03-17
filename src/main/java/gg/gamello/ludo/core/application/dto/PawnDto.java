package gg.gamello.ludo.core.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gg.gamello.ludo.core.domain.pawn.Position;
import lombok.Data;

import java.util.UUID;

@Data
public class PawnDto {

	private UUID id;

	private Position position;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Position possibleMove;

	public PawnDto(UUID id, Position position, Position possibleMove) {
		this.id = id;
		this.position = position;
		this.possibleMove = possibleMove;
	}
}
