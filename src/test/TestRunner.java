package test;

import core.Game;

public class TestRunner {
	
	public static void main(String[] args) {
//		GameGrid grid = new GameGrid(9, 9);
//		System.out.println(grid);
//		grid.completeRow(3);
//		grid.completeRow(5);
//		grid.addBlock(7, 2);
//		grid.addBlock(7, 3);
//		grid.addBlock(7, 4);
//		grid.addBlock(5, 0);
//		grid.addBlock(5, 1);
//		grid.addBlock(5, 2);
////		grid.dropShape(new LShape(), 3);
////		grid.dropBlocks(1, 3);
////		grid.dropGrid(1, 3, 2s);
//		grid.dropShape(1, new ZShape());
//		System.out.println(grid);
//		grid.removeCompletedRows();
//		System.out.println(grid);
		
//		Shape shape = new ZShape();
//		System.out.println(shape);
//		List<Position> edges = shape.getRightEdge();
//		for(Position edge : edges) {
//			System.out.print(edge + " ");
//		}
//		System.out.println(shape);
//		shape = shape.reflection();
//		System.out.println(shape);
//		shape.rotateLeft();
//		System.out.println(shape);
//		shape.rotateLeft();
//		System.out.println(shape);
//		shape.rotateLeft();
//		System.out.println(shape);
		
		Game game = new Game();
		game.startGame();
	}
}
