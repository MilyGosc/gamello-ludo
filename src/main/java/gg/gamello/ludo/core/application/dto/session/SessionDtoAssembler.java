package gg.gamello.ludo.core.application.dto.session;

import gg.gamello.ludo.core.domain.session.Session;

public class SessionDtoAssembler {

	public static SessionDto convertDefault(Session session) {
		return new SessionDto(session.getId(), session.getPlayerId(), session.getGameId());
	}
}
