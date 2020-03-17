package gg.gamello.ludo.core.infrastructure.interceptor;

import gg.gamello.dev.authentication.model.JwtAuthenticationToken;
import gg.gamello.dev.authentication.security.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChannelConnectionInterceptor implements ChannelInterceptor {

	private final String AUTHORIZATION_HEADER = "Authorization";

	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;

	@Autowired
	@Qualifier("clientOutboundChannel")
	private MessageChannel clientOutboundChannel;

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		try {
			if (accessor == null || accessor.getCommand() != StompCommand.CONNECT)
				return message;
			String header = Optional.ofNullable(accessor.getFirstNativeHeader(AUTHORIZATION_HEADER))
					.orElseThrow(() -> new BadCredentialsException("Authorization header is empty"));
			String token = header.replace("Bearer", "").trim();
			accessor.setUser(jwtAuthenticationProvider.authenticate(new JwtAuthenticationToken(token)));
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
}
