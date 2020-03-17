package gg.gamello.ludo.gamemode.application;

import gg.gamello.ludo.gamemode.application.dto.GameModeDetailsDto;
import gg.gamello.ludo.gamemode.application.dto.GameModeDto;
import gg.gamello.ludo.gamemode.application.dto.GameModeDtoAssembler;
import gg.gamello.ludo.gamemode.domain.GameMode;
import gg.gamello.ludo.gamemode.domain.GameModeRepository;
import gg.gamello.ludo.infrastructure.exception.AggregateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameModeApplicationService {

	@Autowired
	private GameModeRepository gameModeRepository;

	@Autowired
	private GameModeDtoAssembler gameModeDtoAssembler;

	@Cacheable("gameModes")
	public List<GameModeDto> getAll() {
		List<GameMode> gameModes = gameModeRepository.findAll();
		return gameModes.stream()
				.map(gameMode -> gameModeDtoAssembler.convertDefault(gameMode))
				.collect(Collectors.toList());
	}

	@Cacheable(value = "gameMode", key = "#name")
	public GameModeDetailsDto getDetails(String name) throws AggregateNotFoundException {
		GameMode gameMode = gameModeRepository.findById(name)
				.orElseThrow(() -> new AggregateNotFoundException(name, "GameMode does not exists"));
		return gameModeDtoAssembler.convertDetails(gameMode);
	}
}
