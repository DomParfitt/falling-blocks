package javafx;

import core.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameApp extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Game game = new Game();
		KeyHandler controller = new KeyHandler(game);
		GameGridComponent gameGridComponent = new GameGridComponent(9, 9);
		NextBlockComponent nextBlockComponent = new NextBlockComponent();
		HBox hbox = new HBox();
		hbox.getChildren().add(gameGridComponent);
		hbox.getChildren().add(nextBlockComponent);
//		gameGridComponent.addController(controller);
		game.addObserver(gameGridComponent);
		game.addObserver(nextBlockComponent);
//		Scene scene = new Scene(gameGridComponent, 500, 500);
//		Scene scene = new Scene(nextBlockComponent, 500, 500);
		Scene scene = new Scene(hbox, 500, 500);
		scene.setOnKeyPressed(controller);
		scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		stage.setScene(scene);
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				Platform.exit();
				System.exit(0);
			}
			
		});
		
		stage.show();
		game.start();
	}

}
