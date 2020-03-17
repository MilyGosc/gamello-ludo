package gg.gamello.ludo.core.application.dto;

import gg.gamello.ludo.core.domain.player.Player;

import java.util.stream.Collectors;

public class PlayerDtoAssembler {

	public static PlayerDto convertDefault(Player player) {
		PlayerDto playerDto = new PlayerDto();
		playerDto.setId(player.getId());
		playerDto.setPawns(player.getPawns().stream()
				.map(PawnDtoAssembler::convertDefault)
				.collect(Collectors.toList()));
		playerDto.setSlot(new SlotDto(player.getSlot().getStart(), player.getSlot().getFinish()));
		return playerDto;
	}
}
