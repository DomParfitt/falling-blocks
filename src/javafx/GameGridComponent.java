package javafx;

import java.util.Observable;
import java.util.Observer;

import core.Game;
import core.GameGrid;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

public class GameGridComponent extends GridPane implements Observer {

	private int rows, cols;

	public GameGridComponent(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				add(new EmptySpace(), j, i);
			}
		}
	}

	@Override
	public void update(Observable obs, Object obj) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Game game = (Game) obs;
				GameGrid grid = game.getGrid();
				getChildren().clear();
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						if (grid.isOccupied(i, j)) {
							add(new Block(), j, i);
						} else {
							add(new EmptySpace(), j, i);
						}
					}
				}
			}
		});

	}

}
