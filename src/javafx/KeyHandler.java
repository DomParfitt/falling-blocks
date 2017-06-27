package javafx;

import core.Game;
import core.GameAction;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {
	
	private Game game;
	
	public KeyHandler(Game game) {
		this.game = game;
	}

	@Override
	public void handle(KeyEvent event) {
		switch (event.getCode()) {
		case LEFT:
			game.enqueue(GameAction.MOVE_LEFT);
			break;
		case RIGHT:
			game.enqueue(GameAction.MOVE_RIGHT);
			break;
		case DOWN:
			game.enqueue(GameAction.MOVE_DOWN);
			break;
		case A:
			game.enqueue(GameAction.ROTATE_LEFT);
			break;
		case D:
			game.enqueue(GameAction.ROTATE_RIGHT);
			break;
		default:
			break;

		}

	}

}
