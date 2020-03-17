package gg.gamello.ludo.core.application.listener;

import gg.gamello.ludo.core.application.message.Action;
import gg.gamello.ludo.core.application.message.GameMessage;
import gg.gamello.ludo.core.domain.event.PawnMoveEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class PawnMoveEventListener implements ApplicationListener<PawnMoveEvent> {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Override
	public void onApplicationEvent(PawnMoveEvent event) {
		GameMessage message = GameMessage.builder()
				.subject(event.getPlayerId().toString())
				.action(Action.PAWN_MOVE)
				.addData("pawnId", event.getPawnId())
				.addData("position", event.getPosition())
				.build();
		messagingTemplate.convertAndSend("/topic/ludo/game/" + event.getGameId(), message);
	}
}
