package gg.gamello.ludo.core.domain.event;

import gg.gamello.ludo.core.domain.Game;
import lombok.Getter;

import java.util.UUID;

public class PlayerJoinEvent extends GameEvent {

	@Getter
	private UUID playerId;

	public PlayerJoinEvent(Game game, UUID playerId) {
		super(game);
		this.playerId = playerId;
	}
}
