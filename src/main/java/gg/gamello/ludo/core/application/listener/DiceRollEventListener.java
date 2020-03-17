package gg.gamello.ludo.core.application.listener;

import gg.gamello.ludo.core.application.dto.PawnDto;
import gg.gamello.ludo.core.application.dto.PawnDtoAssembler;
import gg.gamello.ludo.core.application.message.Action;
import gg.gamello.ludo.core.application.message.GameMessage;
import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.event.DiceResultEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiceRollEventListener implements ApplicationListener<DiceResultEvent> {

	@Autowired
	public SimpMessagingTemplate messagingTemplate;

	@Override
	public void onApplicationEvent(DiceResultEvent event) {
		List<PawnDto> movablePawns = event.getMovablePawns().stream()
				.map(PawnDtoAssembler::convertDefault)
				.collect(Collectors.toList());
		GameMessage message = GameMessage.builder()
				.subject(event.getPlayerId().toString())
				.action(Action.DICE_RESULT)
				.addData("result", event.getResult())
				.addData("movablePawns", movablePawns)
				.build();
		messagingTemplate.convertAndSend("/topic/ludo/game/" + event.getGameId(), message);
	}
}
