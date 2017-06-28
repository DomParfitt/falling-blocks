package javafx;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import core.Game;
import core.GameGrid;
import core.Position;
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

	// TODO: Check this
	@Override
	public void update(Observable obs, Object arg1) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Game game = (Game) obs;
				Shape nextShape = game.getNextShape();
				int height = nextShape.getHeight();
				int width = nextShape.getWidth();

				int startingY = (6 - height) / 2;
				int startingX = (6 - width) / 2;

				GameGrid grid = game.getGrid();

				List<Position> positions = nextShape.getOccupiedSpaces();

				getChildren().clear();
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						add(new EmptySpace(), j, 5 - i);
					}
				}

				for (Position position : positions) {
					add(new Block(), startingX + position.getX(), 5 - (startingY + position.getY()));
				}
			}
		});

	}

}
