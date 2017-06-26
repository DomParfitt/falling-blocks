package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	
	private GameGrid grid;
	private int score;
	
	public Game() {
		this.grid = new GameGrid(9, 9);
		this.score = 0;
	}
	
	public void startGame() {
		List<Shape> shapes = new ArrayList<>();
		shapes.add(new LShape());
		shapes.add(new ZShape());
		shapes.add(new TShape());
		Random shapeSelect = new Random();
		Random colSelect = new Random();
		for(int i = 0; i < 10; i++) {
			System.out.println("Shape Number " + (i+1));
			int row = grid.dropShape(colSelect.nextInt(6), shapes.get(shapeSelect.nextInt(shapes.size())));
			score += grid.removeCompletedRows();
			System.out.println("Score: " + score);
			System.out.println();
			
			if(row == 0) {
				break;
			}
		}
	}

}
