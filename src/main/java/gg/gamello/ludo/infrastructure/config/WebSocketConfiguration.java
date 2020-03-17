package gg.gamello.ludo.infrastructure.config;

import gg.gamello.ludo.core.infrastructure.interceptor.ChannelConnectionInterceptor;
import gg.gamello.ludo.core.infrastructure.interceptor.ChannelSubscriptionInterceptor;
import gg.gamello.ludo.infrastructure.properties.WebSocketProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Autowired
	private WebSocketProperties websocketProperties;

	@Autowired
	private ChannelConnectionInterceptor channelConnectionInterceptor;

	@Autowired
	private ChannelSubscriptionInterceptor channelSubscriptionInterceptor;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableStompBrokerRelay(websocketProperties.getRelay().getDestinationPrefixes())
				.setAutoStartup(true)
				.setRelayHost(websocketProperties.getRelay().getHost())
				.setRelayPort(websocketProperties.getRelay().getPort())
				.setSystemLogin(websocketProperties.getCredentials().getSystemLogin())
				.setSystemPasscode(websocketProperties.getCredentials().getSystemPassword());
		registry.setApplicationDestinationPrefixes(websocketProperties.getApplicationDestinationPrefixes());
		registry.setUserDestinationPrefix(websocketProperties.getUserDestinationPrefix());
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(websocketProperties.getStomp().getEndpoint())
				.setAllowedOrigins(websocketProperties.getStomp().getAllowedOrigins())
				.withSockJS().setSessionCookieNeeded(false).setWebSocketEnabled(false);
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(channelConnectionInterceptor, channelSubscriptionInterceptor);
	}
}
