package gg.gamello.ludo.core.application;

import gg.gamello.ludo.core.application.command.CreateGameCommand;
import gg.gamello.ludo.core.application.dto.GameDto;
import gg.gamello.ludo.core.application.dto.GameDtoAssembler;
import gg.gamello.ludo.core.application.message.GameMessage;
import gg.gamello.ludo.core.domain.Game;
import gg.gamello.ludo.core.domain.GameFactory;
import gg.gamello.ludo.core.domain.GameRepository;
import gg.gamello.ludo.core.infrastructure.exception.GameOperationException;
import gg.gamello.ludo.infrastructure.exception.AggregateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameApplicationService {

	@Autowired
	private GameFactory gameFactory;

	@Autowired
	private GameRepository gameRepository;

	public void create(CreateGameCommand command) throws AggregateNotFoundException {
		Game game = gameFactory.create(command);
		gameRepository.save(game);
	}

	public GameDto get(UUID gameId) throws AggregateNotFoundException {
		return gameRepository.findById(gameId)
				.map(GameDtoAssembler::convertDefault)
				.orElseThrow(() -> new AggregateNotFoundException(gameId.toString(), "game does not exists"));
	}

	public void playerJoin(UUID gameId, UUID playerId) throws AggregateNotFoundException, GameOperationException {
		Game game = findGame(gameId);
		game.playerJoin(playerId);
		gameRepository.save(game);
	}

	public void playerLeave(UUID gameId, UUID playerId) throws AggregateNotFoundException, GameOperationException {
		Game game = findGame(gameId);
		game.playerLeave(playerId);
		gameRepository.save(game);
	}

	public void diceRoll(UUID gameId, GameMessage message) throws AggregateNotFoundException, GameOperationException {
		Game game = findGame(gameId);
		game.diceRoll(UUID.fromString(message.getSubject())); // todo: check if subject is valid
		gameRepository.save(game);
	}

	public void pawnMove(UUID gameId, GameMessage message) throws AggregateNotFoundException, GameOperationException {
		Game game = findGame(gameId);
		game.pawnSelect(UUID.fromString(message.getSubject()),
				UUID.fromString(String.valueOf(message.getData().get("pawnId"))));
		gameRepository.save(game);
	}

	public boolean containsPlayer(UUID gameId, UUID playerId) {
		Game game = gameRepository.findById(gameId).orElse(null);
		if (game == null || game.getBoard() == null)
			return false;
		return game.getBoard().containsPlayer(playerId);
	}

	private Game findGame(UUID gameId) throws AggregateNotFoundException {
		return gameRepository.findById(gameId)
				.orElseThrow(() -> new AggregateNotFoundException(gameId.toString(), "game does not exists"));
	}
}
