package gg.gamello.ludo.core.application.listener;

import gg.gamello.ludo.core.application.message.Action;
import gg.gamello.ludo.core.application.message.GameMessage;
import gg.gamello.ludo.core.domain.event.GameFinishEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameFinishListener implements ApplicationListener<GameFinishEvent> {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Override
	public void onApplicationEvent(GameFinishEvent event) {
		GameMessage message = GameMessage.builder()
				.subject("GAME")
				.action(Action.GAME_FINISH)
				.addData("result", event.getResult())
				.build();
		messagingTemplate.convertAndSend("/topic/ludo/game/" + event.getGameId(), message);
	}
}
