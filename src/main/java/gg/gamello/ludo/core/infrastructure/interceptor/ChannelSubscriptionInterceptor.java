package gg.gamello.ludo.core.infrastructure.interceptor;

import gg.gamello.dev.authentication.model.JwtAuthentication;
import gg.gamello.dev.authentication.model.User;
import gg.gamello.ludo.core.application.GameApplicationService;
import gg.gamello.ludo.core.application.SessionApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.UUID;

@Slf4j
@Component
public class ChannelSubscriptionInterceptor implements ChannelInterceptor {

	@Autowired
	private GameApplicationService gameApplicationService;

	@Autowired
	private SessionApplicationService sessionApplicationService;

	@Autowired
	@Qualifier("clientOutboundChannel")
	private MessageChannel clientOutboundChannel;

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		try {
			if (accessor == null || accessor.getCommand() != StompCommand.SUBSCRIBE)
				return message;
			if (accessor.getDestination() != null && accessor.getDestination().startsWith("/topic/ludo/game/")) {
				String gameId = accessor.getDestination().replace("/topic/ludo/game/", "");
				User user = getUser(accessor.getUser());
				if (gameApplicationService.containsPlayer(UUID.fromString(gameId), user.getId())) {
					sessionApplicationService.add(accessor.getSessionId(), user.getId(), UUID.fromString(gameId));
					return message;
				}
				closeConnection(accessor.getSessionId(), "Not participating in this game");
			}
		} catch (Exception e) {
			closeConnection(accessor.getSessionId(), e.getMessage());
		}
		return message;
	}

	private void closeConnection(String sessionId, String message) {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
		accessor.setMessage(message);
		accessor.setSessionId(sessionId);
		this.clientOutboundChannel.send(MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders()));
	}

	private User getUser(Principal principal) {
		JwtAuthentication authentication = (JwtAuthentication) principal;
		return (User) authentication.getPrincipal();
	}
}
