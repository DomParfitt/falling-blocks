package core;

import java.util.ArrayList;
import java.util.List;

public class ActionQueue {
	
	private List<GameAction> queue;
	
	public ActionQueue() {
		this.queue = new ArrayList<>();
	}
	
	public boolean isEmpty() {
		return queue.size() == 0;
	}
	
	public synchronized void enqueue(GameAction action) {
		queue.add(action);
		notifyAll();
	}
	
	public synchronized GameAction dequeue() {
		while(this.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GameAction action = queue.remove(0);
		notifyAll();
		return action;
	}
}
