package gg.gamello.ludo.core.application.listener;

import gg.gamello.ludo.core.application.message.Action;
import gg.gamello.ludo.core.application.message.GameMessage;
import gg.gamello.ludo.core.domain.event.RoundNewEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RoundNewEventListener implements ApplicationListener<RoundNewEvent> {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Override
	public void onApplicationEvent(RoundNewEvent event) {
		GameMessage message = GameMessage.builder()
				.subject(event.getPlayerId().toString())
				.action(Action.ROUND_NEW)
				.addData("playerId", event.getPlayerId())
				.build();
		messagingTemplate.convertAndSend("/topic/ludo/game/" + event.getGameId(), message);
	}
}
