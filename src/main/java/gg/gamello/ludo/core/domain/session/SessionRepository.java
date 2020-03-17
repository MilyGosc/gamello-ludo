package gg.gamello.ludo.core.domain.session;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends CrudRepository<Session, String> {
	List<Session> findAllByPlayerIdAndGameId(UUID playerId, UUID gameId);

	Optional<Session> findFirstByPlayerId(UUID playerId);
	Optional<Session> findFirstByPlayerIdAndGameId(UUID playerId, UUID gameId);

	void deleteAllByGameId(UUID gameId);
}
