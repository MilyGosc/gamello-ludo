package gg.gamello.ludo.core.application.dispatcher;

import gg.gamello.ludo.core.application.GameApplicationService;
import gg.gamello.ludo.core.application.message.GameMessage;
import gg.gamello.ludo.core.infrastructure.exception.GameMessagingException;
import gg.gamello.ludo.core.infrastructure.exception.GameOperationException;
import gg.gamello.ludo.infrastructure.exception.AggregateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class GameMessageDispatcher {

	@Autowired
	private GameApplicationService gameApplicationService;

	public void dispatch(UUID gameId, GameMessage message) throws GameMessagingException, AggregateNotFoundException, GameOperationException {
		switch (message.getAction()) {
			case DICE_ROLL:
				gameApplicationService.diceRoll(gameId, message);
				break;
			case PAWN_SELECT:
				gameApplicationService.pawnMove(gameId, message);
				break;
			default:
				throw new GameMessagingException("not client action");
		}
	}
}
