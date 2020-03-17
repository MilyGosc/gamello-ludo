package gg.gamello.ludo.core.domain.dice;

import org.springframework.stereotype.Component;

@Component
public class DiceFactory {

	public Dice create() {
		return new Dice();
	}
}
