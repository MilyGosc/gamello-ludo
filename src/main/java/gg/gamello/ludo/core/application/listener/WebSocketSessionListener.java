package gg.gamello.ludo.core.application.listener;

import gg.gamello.ludo.core.application.GameApplicationService;
import gg.gamello.ludo.core.application.SessionApplicationService;
import gg.gamello.ludo.core.application.dto.session.SessionDto;
import gg.gamello.ludo.core.application.message.Action;
import gg.gamello.ludo.core.application.message.GameMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Slf4j
@Component
public class WebSocketSessionListener {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private GameApplicationService gameApplicationService;

	@Autowired
	private SessionApplicationService sessionApplicationService;

	@Async("gameExecutor")
	@EventListener
	public void onSubscribeEvent(SessionSubscribeEvent event) {
		try {
			StompHeaderAccessor accessor = getMessageHeaders(event.getMessage());
			if (accessor.getDestination() != null && !accessor.getDestination().startsWith("/topic/ludo/game"))
				return;
			SessionDto session = sessionApplicationService.get(accessor.getSessionId());
			gameApplicationService.playerJoin(session.getGameId(), session.getPlayerId());
			GameMessage message = GameMessage.builder()
					.subject("GAME")
					.action(Action.GAME_STATE)
					.addData("game", gameApplicationService.get(session.getGameId()))
					.build();
			messagingTemplate.convertAndSendToUser(accessor.getUser().getName(), "/queue/ludo", message);
		} catch (Exception e) {
			log.debug("error occurred in subscribe event handler: " + e.getMessage());
		}
	}

	@Async("gameExecutor")
	@EventListener
	public void onDisconnectEvent(SessionDisconnectEvent event) {
		try {
			StompHeaderAccessor accessor = getMessageHeaders(event.getMessage());
			SessionDto session = sessionApplicationService.delete(accessor.getSessionId());
			if (!sessionApplicationService.isPlayerInGame(session.getPlayerId(), session.getGameId()))
				gameApplicationService.playerLeave(session.getGameId(), session.getPlayerId());
		} catch (Exception e) {
			log.debug("error occurred in disconnect event handler: " + e.getMessage());
		}
	}

	private StompHeaderAccessor getMessageHeaders(Message<?> message) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		if (accessor == null)
			throw new IllegalStateException("Accessor can not be null");
		return accessor;
	}
}
