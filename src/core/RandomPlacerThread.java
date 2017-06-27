package core;

import java.util.Random;

public class RandomPlacerThread extends GameThread {

	private long waitTime;

	public RandomPlacerThread(Game game, long waitTime) {
		super(game);
		this.waitTime = waitTime;
	}

	@Override
	public void run() {
		Random rand = new Random();
		GameAction[] actions = GameAction.values();
		while (!game.getGrid().hasGameEnded()) {
			game.enqueue(actions[rand.nextInt(actions.length - 1)]);
			synchronized (this) {
				try {
					wait(waitTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
