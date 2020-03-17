package gg.gamello.ludo.core.infrastructure.advisor;

import gg.gamello.dev.authentication.model.User;
import gg.gamello.ludo.core.application.message.Action;
import gg.gamello.ludo.core.application.message.GameMessage;
import gg.gamello.ludo.core.infrastructure.exception.GameMessagingException;
import gg.gamello.ludo.core.infrastructure.exception.GameOperationException;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GameMessageAdvisor {

	@SendToUser("/queue/ludo")
	@MessageExceptionHandler({GameOperationException.class, GameMessagingException.class})
	public GameMessage gameExceptionHandler(Exception exception, @AuthenticationPrincipal User user) {
		return GameMessage.builder()
				.subject(user.getId().toString())
				.action(Action.ERROR)
				.addData("message", exception.getMessage())
				.build();
	}

	@SendToUser("/queue/ludo")
	@MessageExceptionHandler(MessageConversionException.class)
	public GameMessage messageConversionHandler(MessageConversionException exception, @AuthenticationPrincipal User user) {
		return GameMessage.builder()
				.subject(user.getId().toString())
				.action(Action.ERROR)
				.addData("message", "parsing message error")
				.build();
	}
}
