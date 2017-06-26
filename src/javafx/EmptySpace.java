package javafx;

import javafx.scene.control.Label;

public class EmptySpace extends Label {

	public EmptySpace() {
		this.getStyleClass().clear();
		this.getStyleClass().add("empty-space");
	}
}
