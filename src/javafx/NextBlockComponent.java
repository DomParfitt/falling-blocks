package javafx;

import java.util.Observable;
import java.util.Observer;

import core.Game;
import core.GameGrid;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import shapes.Shape;

public class NextBlockComponent extends GridPane implements Observer {
	
	
	public NextBlockComponent() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				add(new EmptySpace(), j, i);
			}
		}
		
	}

	//TODO: Check this
	@Override
	public void update(Observable obs, Object arg1) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Game game = (Game) obs;
				Shape nextShape = game.getNextShape();
				GameGrid grid = game.getGrid();
				getChildren().clear();
				for (int i = nextShape.getHeight() - 1; i >= 0; i--) {
					for (int j = 0; j < nextShape.getWidth(); j++) {
						if (grid.isOccupied(i, j)) {
							add(new Block(), j, nextShape.getHeight() - i);
						} else {
							add(new EmptySpace(), j, nextShape.getHeight() - i);
						}
					}
				}
			}
		});
		
	}

}
