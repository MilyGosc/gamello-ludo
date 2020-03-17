package gg.gamello.ludo.core.application.dto.session;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SessionDto {

	private String id;

	private UUID playerId;

	private UUID gameId;
}
