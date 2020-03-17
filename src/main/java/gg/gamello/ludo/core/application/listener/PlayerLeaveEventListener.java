package gg.gamello.ludo.core.application.listener;

import gg.gamello.ludo.core.application.message.Action;
import gg.gamello.ludo.core.application.message.GameMessage;
import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.event.PlayerLeaveEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class PlayerLeaveEventListener implements ApplicationListener<PlayerLeaveEvent> {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Override
	public void onApplicationEvent(PlayerLeaveEvent event) {
		GameMessage message = GameMessage.builder()
				.subject(event.getPlayerId().toString())
				.action(Action.PLAYER_LEAVE)
				.build();
		messagingTemplate.convertAndSend("/topic/ludo/game/" + event.getGameId(), message);
	}
}