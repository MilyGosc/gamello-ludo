package gg.gamello.ludo.core.domain.event;

import gg.gamello.ludo.core.domain.Game;

public class GameStartEvent extends GameEvent {

	public GameStartEvent(Game game) {
		super(game);
	}
}
