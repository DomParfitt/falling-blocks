package core;

public class GameThread extends Thread {

	protected Game game;

	public GameThread(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		game.startGame();
	}
}
