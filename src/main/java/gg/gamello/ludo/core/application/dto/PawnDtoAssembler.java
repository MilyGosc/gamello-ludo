package gg.gamello.ludo.core.application.dto;

import gg.gamello.ludo.core.domain.pawn.Pawn;

public class PawnDtoAssembler {

	public static PawnDto convertDefault(Pawn pawn) {
		return new PawnDto(pawn.getId(), pawn.getPosition(), pawn.getPossibleMove());
	}
}
