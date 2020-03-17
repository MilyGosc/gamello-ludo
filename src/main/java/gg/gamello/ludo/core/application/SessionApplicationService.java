package gg.gamello.ludo.core.application;

import gg.gamello.ludo.core.application.dto.session.SessionDto;
import gg.gamello.ludo.core.application.dto.session.SessionDtoAssembler;
import gg.gamello.ludo.core.domain.session.Session;
import gg.gamello.ludo.core.domain.session.SessionRepository;
import gg.gamello.ludo.core.infrastructure.exception.SessionOperationException;
import gg.gamello.ludo.infrastructure.exception.AggregateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SessionApplicationService {

	@Autowired
	private SessionRepository sessionRepository;

	public void add(String sessionId, UUID playerId, UUID gameId) throws SessionOperationException {
		if (getPlayerInGameSessions(playerId, gameId).size() > 2)
			throw new SessionOperationException("too many open sessions");
		var session = new Session(sessionId, playerId, gameId);
		sessionRepository.save(session);
	}

	public SessionDto delete(String sessionId) throws AggregateNotFoundException {
		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new AggregateNotFoundException(sessionId, "session does not exists"));
		sessionRepository.deleteById(sessionId);
		return SessionDtoAssembler.convertDefault(session);
	}

	public SessionDto get(String sessionId) throws AggregateNotFoundException {
		return sessionRepository.findById(sessionId)
				.map(SessionDtoAssembler::convertDefault)
				.orElseThrow(() -> new AggregateNotFoundException(sessionId, "session does not exists"));
	}

	public boolean isPlayerInAnyGame(UUID playerId) {
		return sessionRepository.findFirstByPlayerId(playerId).isPresent();
	}

	public boolean isPlayerInGame(UUID playerId, UUID gameId) {
		return sessionRepository.findFirstByPlayerIdAndGameId(playerId, gameId).isPresent();
	}

	public void clearForGame(UUID gameId) {
		sessionRepository.deleteAllByGameId(gameId);
	}

	private List<Session> getPlayerInGameSessions(UUID playerId, UUID gameId) {
		return sessionRepository.findAllByPlayerIdAndGameId(playerId, gameId);
	}
}
