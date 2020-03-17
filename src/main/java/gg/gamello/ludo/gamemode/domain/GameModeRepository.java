package gg.gamello.ludo.gamemode.domain;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameModeRepository extends CrudRepository<GameMode, String> {

	@Override
	@EnableScan
	List<GameMode> findAll();
}
