package gg.gamello.ludo.core.domain.event;

import gg.gamello.ludo.core.domain.Game;
import lombok.Getter;

import java.util.UUID;

public class PlayerLeaveEvent extends GameEvent {

	@Getter
	private UUID playerId;

	public PlayerLeaveEvent(Game game, UUID playerId) {
		super(game);
		this.playerId = playerId;
	}
}