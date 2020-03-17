package gg.gamello.ludo.core.domain.pawn;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PawnFactory {

	public Pawn create(int home) {
		return new Pawn(UUID.randomUUID(), home);
	}
}
