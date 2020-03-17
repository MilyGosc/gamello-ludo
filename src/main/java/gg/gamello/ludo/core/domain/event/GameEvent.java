package gg.gamello.ludo.core.domain.event;

import gg.gamello.ludo.core.domain.Game;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class GameEvent extends ApplicationEvent {

	@Getter
	private UUID gameId;

	public GameEvent(Game game) {
		super(game);
		this.gameId = game.getId();
	}
}
