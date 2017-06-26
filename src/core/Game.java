package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Game extends Observable implements Observer {
	
	private GameGrid grid;
	private ShapeFactory shapeFactory;
	private ActionQueue queue;
	private int score;
	
	public Game() {
		this.grid = new GameGrid(9, 9);
		this.grid.addObserver(this);
		this.shapeFactory = new ShapeFactory();
		this.queue = new ActionQueue();
		this.score = 0;
	}
	
	public void enqueue(GameAction action) {
		this.queue.enqueue(action);
	}
	
	public GameAction dequeue() {
		return this.queue.dequeue();
	}
	
	public void start() {
		GameThread thread = new GameThread(this);
		thread.start();
	}
	
	public void startGame() {
		Random colSelect = new Random();
		for(int i = 0; i < 10; i++) {
			System.out.println("Shape Number " + (i+1));
			int row = grid.dropShape(colSelect.nextInt(7), shapeFactory.getShape());
			score += grid.removeCompletedRows();
			setChanged();
			notifyObservers();
			System.out.println("Score: " + score);
			System.out.println();
			
			if(row == 0) {
				break;
			}
		}
	}
	
	public GameGrid getGrid() {
		return this.grid;
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
		
	}

}
