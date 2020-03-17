package gg.gamello.ludo.core.domain.event;

import gg.gamello.ludo.core.domain.Game;
import lombok.Getter;

import java.util.LinkedList;
import java.util.UUID;

public class GameFinishEvent extends GameEvent {

	@Getter
	private LinkedList<UUID> result;

	public GameFinishEvent(Game game, LinkedList<UUID> result) {
		super(game);
		this.result = result;
	}
}
