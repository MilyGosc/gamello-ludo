package gg.gamello.ludo.core.ui;

import gg.gamello.dev.authentication.model.User;
import gg.gamello.ludo.core.application.dispatcher.GameMessageDispatcher;
import gg.gamello.ludo.core.application.message.GameMessage;
import gg.gamello.ludo.core.infrastructure.exception.GameMessagingException;
import gg.gamello.ludo.core.infrastructure.exception.GameOperationException;
import gg.gamello.ludo.infrastructure.exception.AggregateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@Controller
public class GameMessageController {

	@Autowired
	private GameMessageDispatcher gameMessageDispatcher;

	@MessageMapping("/ludo/game/{gameId}")
	public void handleGame(@DestinationVariable UUID gameId, GameMessage message, @AuthenticationPrincipal User user) throws GameMessagingException, AggregateNotFoundException, GameOperationException {
		if (!user.getId().toString().equals(message.getSubject()))
			throw new GameOperationException("Unauthorized");
		gameMessageDispatcher.dispatch(gameId, message);
	}
}
