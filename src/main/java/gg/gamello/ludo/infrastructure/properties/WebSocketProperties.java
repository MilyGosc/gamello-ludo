package gg.gamello.ludo.infrastructure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("websocket")
public class WebSocketProperties {

	/**
	 * Message broker relay configuration
	 */
	private Relay relay;

	/**
	 * Message broker authorization configuration
	 */
	private Credentials credentials;


	/**
	 * Incoming messages prefix
	 */
	private String[] applicationDestinationPrefixes;

	/**
	 * Prefix of private user channel
	 */
	private String userDestinationPrefix;

	/**
	 * STOMP configuration
	 */
	private Stomp stomp;

	@Data
	public static class Relay {

		/**
		 * Relay connection host
		 */
		private String host;


		/**
		 * Relay connection port
		 */
		private int port;

		/**
		 * Relay sending destinations
		 */
		private String[] destinationPrefixes;
	}

	@Data
	public static class Credentials {

		/**
		 * Broker system login
		 */
		private String systemLogin;

		/**
		 * Broker system password
		 */
		private String systemPassword;


		/**
		 * Client login
		 */
		private String clientLogin;


		/**
		 * Client password
		 */
		private String clientPassword;
	}

	@Data
	public static class Stomp {

		/**
		 * Connection path for websocket
		 */
		private String endpoint;

		/**
		 * Sources allowed to connect
		 */
		private String[] allowedOrigins;
	}
}
