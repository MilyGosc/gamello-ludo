package gg.gamello.ludo.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

import static org.springframework.messaging.simp.SimpMessageType.*;

@Configuration
public class WebSocketSecurityConfiguration extends AbstractSecurityWebSocketMessageBrokerConfigurer {

	@Override
	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		messages.simpTypeMatchers(CONNECT, SUBSCRIBE, UNSUBSCRIBE, DISCONNECT, HEARTBEAT).permitAll()
				.simpDestMatchers("/app/**", "/topic/**").authenticated()
				.simpSubscribeDestMatchers("/topic/**", "/user/**").authenticated()
				.anyMessage().denyAll();
	}

	@Override
	protected boolean sameOriginDisabled() {
		return true;
	}
}
