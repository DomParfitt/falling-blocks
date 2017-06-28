package javafx;

import javafx.scene.control.Label;

public class Block extends Label {
	
	public static enum BlockColour {RED, GREEN, BLUE, YELLOW};
	public static int BLOCK_COUNTER = 0;
	public static int SHAPE_COUNTER = 0;
	
	public Block() {
		this.getStyleClass().clear();
		this.getStyleClass().add("block");
		this.getStyleClass().add(BlockColour.values()[SHAPE_COUNTER].toString().toLowerCase());

		//TODO: This works but updates the colours every move
		BLOCK_COUNTER++;
		if(BLOCK_COUNTER >= 4) {
			BLOCK_COUNTER = 0;
			SHAPE_COUNTER++;
		}
		
		if(SHAPE_COUNTER >= BlockColour.values().length) {
			SHAPE_COUNTER = 0;
		}
	}

}
