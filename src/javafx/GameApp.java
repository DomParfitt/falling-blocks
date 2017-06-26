package javafx;

import core.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Game game = new Game();
		GameGridComponent gameGridComponent = new GameGridComponent(9, 9);
		game.addObserver(gameGridComponent);
		Scene scene = new Scene(gameGridComponent, 500, 500);
		scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		game.start();
	}

}
