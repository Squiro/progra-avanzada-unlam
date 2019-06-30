package brains;

import game.Game;
import game.GameDifficulty;
import game.GameMode;

public class App {
	public static void main(String[] args) {
		Game.start(GameMode.NORMAL, 2, 2, GameDifficulty.NORMAL, new MyBrain());
	}
}


/**
 * Comandos:
 * 1 -> Aumenta movimientos / seg
 * 2 -> Disminuye movimientos / seg
 * 3 -> 10 movimientos / seg
 * 4 -> 2 movimientos / seg
 * P -> Pausa
 * R -> Siguiente ronda
 */
