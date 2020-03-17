package gg.gamello.ludo.core.domain.player;

import gg.gamello.ludo.core.domain.dice.DiceFactory;
import gg.gamello.ludo.core.domain.pawn.Pawn;
import gg.gamello.ludo.core.domain.pawn.PawnFactory;
import gg.gamello.ludo.gamemode.application.GameModeApplicationService;
import gg.gamello.ludo.gamemode.application.dto.GameModeDetailsDto;
import gg.gamello.ludo.infrastructure.exception.AggregateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PlayerFactory {

	@Autowired
	private DiceFactory diceFactory;

	@Autowired
	private PawnFactory pawnFactory;

	@Autowired
	private GameModeApplicationService gameModeApplicationService;

	public Player create(UUID playerId, int slot, String gameModeName) throws AggregateNotFoundException {
		GameModeDetailsDto gameMode = gameModeApplicationService.getDetails(gameModeName);
		List<Pawn> pawns = new ArrayList<>(gameMode.getPawns());
		for (int i = 0; i < gameMode.getPawns(); i++)
			pawns.add(pawnFactory.create(i));
		return new Player(playerId, pawns, diceFactory.create(), createSlot(gameMode.getSlots().get(slot)));
	}

	private Slot createSlot(gg.gamello.ludo.gamemode.domain.Slot slot) {
		return new Slot(slot.getStart(), slot.getFinish());
	}
}
