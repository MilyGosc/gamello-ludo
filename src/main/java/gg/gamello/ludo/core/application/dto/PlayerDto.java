package gg.gamello.ludo.core.application.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PlayerDto {

	private UUID id;

	private List<PawnDto> pawns;

	private SlotDto slot;
}
