package gg.gamello.ludo.core.application.command;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateGameCommand {

	private String gameModeName;

	private List<UUID> playersIds;
}
