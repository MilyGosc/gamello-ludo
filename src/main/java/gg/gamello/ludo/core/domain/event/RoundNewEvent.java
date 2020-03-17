package gg.gamello.ludo.core.domain.event;

import gg.gamello.ludo.core.domain.Game;
import lombok.Getter;

import java.util.UUID;

public class RoundNewEvent extends GameEvent {

	@Getter
	private UUID playerId;

	public RoundNewEvent(Game game, UUID playerId) {
		super(game);
		this.playerId = playerId;
	}
}
