package gg.gamello.ludo.gamemode.application.dto;

import gg.gamello.ludo.gamemode.domain.Slot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GameModeDetailsDto extends GameModeDto implements Serializable {

	private List<Slot> slots;

	private int squares;

	private int pawns;
}
