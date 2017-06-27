package core;

public class ActionPlacerThread extends GameThread {

	private long waitTime;
	
	public ActionPlacerThread(Game game, long waitTime) {
		super(game);
		this.waitTime = waitTime;
	}
	
	@Override
	public void run() {
		while(!game.getGrid().hasGameEnded()) {
			game.enqueue(GameAction.MOVE_DOWN);
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
