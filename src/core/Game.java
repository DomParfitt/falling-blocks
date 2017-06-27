package core;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import shapes.Shape;
import shapes.ShapeFactory;

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
		Random rand = new Random();
		ActionPlacerThread placer = new ActionPlacerThread(this, 1000);
		placer.start();
		
		RandomPlacerThread randomPlacer = new RandomPlacerThread(this, 300);
		randomPlacer.start();
		
		while (true) { //TODO: Someway to declare end condition
			if (grid.hasActiveShape()) {
				GameAction action = queue.dequeue();
				System.out.println(action);
				switch (action) {
				case MOVE_DOWN:
					grid.moveShapeDown();
					break;
				case MOVE_LEFT:
					grid.moveShapeLeft();
					break;
				case MOVE_RIGHT:
					grid.moveShapeRight();
					break;
				case ROTATE_LEFT:
					grid.rotateShapeLeft();
					break;
				case ROTATE_RIGHT:
					grid.rotateShapeRight();
					break;
				default:
					break;

				}
			} else {
				grid.removeCompletedRows();
				System.out.println("Dropping new shape");
				Shape shape = shapeFactory.getShape();
				grid.addNewShape(shape, rand.nextInt(7)); //TODO: Remove magic number
			}
			System.out.println(grid);
//			if(grid.hasGameEnded()) {
//				break;
//			}
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
