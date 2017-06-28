package core;

import java.util.ArrayList;
import java.util.List;

import shapes.Shape;

/**
 * Class for the main grid the game runs on
 * @author Dom Parfitt
 *
 */
public class GameGrid extends Grid {

	private boolean hasActiveShape;
	private Shape shape;
	private boolean hasGameEnded;

	/**
	 * Intialise an instance with a number of columns and rows
	 * @param cols
	 * @param rows
	 */
	public GameGrid(int cols, int rows) {
		super(cols, rows);
		hasActiveShape = false;
		hasGameEnded = false;
	}
	
	/**
	 * Check whether there is an active shape
	 * @return true if there is an active shape, false otherwise
	 */
	public boolean hasActiveShape() {
		return hasActiveShape;
	}
	
	/**
	 * Check whether the game has ended
	 * @return true if the game has ended, false otherwise
	 */
	public boolean hasGameEnded() {
		return hasGameEnded;
	}

	/**
	 * FOR TEST USE ONLY.
	 * 
	 * @param rowNumber
	 */
	@Deprecated
	public void completeRow(int rowNumber) {
		Row row = getRow(rowNumber);
		for (int i = 0; i < row.getSize(); i++) {
			row.addBlock(i);
		}
	}

	/**
	 * Removes any completed rows from the grid and replaces them with empty
	 * rows at the top of the grid
	 */
	public int removeCompletedRows() {
		int count = 0;
		List<Row> rowsToRemove = new ArrayList<>();
		for (Row row : grid) {
			if (row.isComplete()) {
				rowsToRemove.add(row);
			}
		}

		for (Row row : rowsToRemove) {
			removeRow(row);
			addRow(new Row(cols));
			count++;
		}

		return count;
	}

	/**
	 * Drops a single block down the given column. Block stops as soon as it
	 * hits another block or the bottom of the grid
	 * 
	 * @param col
	 *            the column to drop down
	 */
	@Deprecated
	public void dropBlock(int col) {
		for (int row = 0; row < rows; row++) {
			if (getRow(row).isOccupied(col)) {
				break;
			}
			addBlock(row, col);
			if (row > 0) {
				getRow(row - 1).removeBlock(col);
			}
			System.out.println(this);
		}
	}

	/**
	 * Drops a horizontal line of blocks
	 * 
	 * @param col
	 *            the column that the leftmost block drops from
	 * @param numBlocks
	 *            the number of blocks in the line
	 */
	@Deprecated
	public void dropBlocks(int col, int numBlocks) {
		// for (int row = rows - 1; row >= 0; row--) {
		for (Row row : this) {
			int rowNum = grid.indexOf(row);
			for (int i = 0; i < numBlocks; i++) {
				if (isOccupied(rowNum, col + i)) {
					return;
				}
				addBlock(rowNum, col + i);
				if (rowNum < rows - 1) {
					removeBlock(rowNum + 1, col + i);
				}

			}
			System.out.println(this);
		}
	}

	/**
	 * Drop a grid of blocks
	 * 
	 * @param col
	 *            the column to start from
	 * @param width
	 *            the width (in blocks) of the grid
	 * @param height
	 *            the height (in blocks) of the grid
	 */
	@Deprecated
	public void dropGrid(int col, int width, int height) {
		for (int row = rows - 1; row >= 0; row--) {
			// for (int gridRow = height - 1; gridRow >= 0; gridRow--) {
			for (int rowOffset = 0; rowOffset < height; rowOffset++) {
				for (int i = 0; i < width; i++) {
					if (row + rowOffset < rows) {
						if (isOccupied(row + rowOffset, col + i)) {
							// return;
						}
						addBlock(row + rowOffset, col + i);
						if (row + rowOffset < rows - 1) {
							removeBlock(row + rowOffset + 1, col + i);
						}
					}

				}
			}
			System.out.println(this);
		}
	}

	/**
	 * Drop a shape from a given column
	 * 
	 * @param col
	 *            the column to drop the shape from
	 * @param shape
	 *            the shape to drop
	 */
	// TODO: It's clunky having this be Observable, split this method out into
	// a single downwards movement that must be called repeatedly (this
	// should also make it easier to implement controlling block)
	@Deprecated
	public synchronized int dropShape(int col, Shape shape) {
		int returnRow = 0;

		// Repeat for every row in the grid
		// Starts from an imaginary row to accommodate the whole shape
		for (int row = 0 - shape.getHeight() + 1; row < this.rows; row++) {
			returnRow = row;
			if (canProgress(row, col, shape)) {
				// Repeat for each row in the shape
				for (int rowOffset = shape.getHeight() - 1; rowOffset >= 0; rowOffset--) {
					// for (int rowOffset = 0; rowOffset < shape.getHeight();
					// rowOffset++) {

					// Repeat for each column of each row of the shapes
					for (int colOffset = 0; colOffset < shape.getWidth(); colOffset++) {

						//
						if (rowOffset + row >= 0) {

							// Moves the shape downwards
							if (shape.isOccupied(rowOffset, colOffset)) {
								// System.out.println("Shape is occupied");
								addBlock(rowOffset + row, col + colOffset);
							}

							// Removes the blocks from where the shape was
							if (rowOffset + row > 0) {
								removeBlock(rowOffset + row - 1, col + colOffset);
							}
						}
					}
				}
				System.out.println(this);
				setChanged();
				notifyObservers();
				try {
					wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Row at break was: " + row);
				break;
			}
		}

		return returnRow;
	}
	
	/**
	 * Adds a new shape to be dropped
	 * @param shape the shape to be dropped
	 * @param col the column to drop from
	 */
	public void addNewShape(Shape shape, int col) {
		addShape(shape, rows, col);
//		if(!canMoveShapeDown()) {
//			hasGameEnded = true;
//			hasActiveShape = false;
//		}
	}

	/**
	 * Adds a shape at a given position
	 * @param shape the shape to add
	 * @param row the row to add the shape on
	 * @param col the column to add the shape on
	 */
	private void addShape(Shape shape, int row, int col) {
		if (!hasActiveShape) {
			
			this.shape = shape;
			this.shape.setPosition(col, row);
			List<Position> positions = this.shape.getOccupiedSpaces();
			for (Position position : positions) {
				int trueRow = row + position.getY();
				int trueCol = col + position.getX();
				if (trueRow < rows && trueCol < cols) {
//					System.out.println("AddBlock - row: " + (row + position.getY()) + "; col: " + (col + position.getX()));
					addBlock(trueRow, trueCol);
				}
			}
			hasActiveShape = true;
			
		}

	}

	/**
	 * Remove the shape from its current position
	 * @param shape the shape to remove
	 */
	private void removeShape() {
		if (hasActiveShape) {
			int row = shape.getYPosition();
			int col = shape.getXPosition();
			List<Position> positions = shape.getOccupiedSpaces();
			for (Position position : positions) {
				int trueRow = row + position.getY();
				int trueCol = col + position.getX();
				if (trueRow < rows) {
					removeBlock(trueRow, trueCol);
				}
			}
			hasActiveShape = false;
		}

	}
	

	/**
	 * Move the active shape left if possible
	 */
	public void moveShapeLeft() {
		if (hasActiveShape && canMoveShapeLeft()) {
			removeShape();
			addShape(shape, shape.getYPosition(), shape.getXPosition() - 1);
		}
	}

	/**
	 * Move the active shape right if possible
	 */
	public void moveShapeRight() {
		if (hasActiveShape && canMoveShapeRight()) {
			removeShape();
			addShape(shape, shape.getYPosition(), shape.getXPosition() + 1);
		}
	}
	
	/**
	 * Move the active shape down if possible and if not
	 * make the shape inactive
	 */
	public void moveShapeDown() {
		if (hasActiveShape) {
			if(canMoveShapeDown()) {
				removeShape();
				addShape(shape, shape.getYPosition() - 1, shape.getXPosition());
			} else {
				hasActiveShape = false;
				if(shape.getYPosition() >= rows) {
					hasGameEnded = true;
				}
			}
		}
	}
	
	/**
	 * Rotated the active shape anti-clockwise
	 */
	public void rotateShapeLeft() {
		if(hasActiveShape && canMoveShapeLeft()) {
			removeShape();
			shape.rotateLeft();
			addShape(shape, shape.getYPosition(), shape.getXPosition());
		}
	}
	
	/**
	 * Rotated the active shape clockwise
	 */
	public void rotateShapeRight() {
		if(hasActiveShape && canMoveShapeRight()) {
			removeShape();
			shape.rotateRight();
			addShape(shape, shape.getYPosition(), shape.getXPosition());
		}
	}

	/**
	 * Check if the active shape can move down
	 * @return true if the shape can move down, false otherwise
	 */
	private boolean canMoveShapeDown() {
		List<Position> edges = shape.getBottomEdge();
		for(Position edge : edges) {
			int row = edge.getY() + shape.getYPosition();
			int col = edge.getX() + shape.getXPosition();
			if(row < rows && isOccupied(row - 1, col)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if the active shape can move left
	 * @return true if the shape can move left, false otherwise
	 */
	private boolean canMoveShapeLeft() {
		List<Position> edges = shape.getLeftEdge();
		for(Position edge : edges) {
			int row = edge.getY() + shape.getYPosition();
			int col = edge.getX() + shape.getXPosition();
			if(row < rows && (col - 1 < 0 || isOccupied(row, col - 1))){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if the active shape can move right
	 * @return true if the shape can move right, false otherwise
	 */
	private boolean canMoveShapeRight() {
		List<Position> edges = shape.getRightEdge();
		for(Position edge : edges) {
			int row = edge.getY() + shape.getYPosition();
			int col = edge.getX() + shape.getXPosition();
			if(row < rows && (col + 1 >= cols || isOccupied(row, col + 1))) {
				return false;
			}
		}
		return true;
	}
	
	@Deprecated
	private boolean canProgress(int row, int col, Shape shape) {
		if (row >= 0) {
			for (int rowOffset = shape.getHeight() - 1; rowOffset >= 0; rowOffset--) {
				// System.out.println("RowOffset: " + rowOffset);
				// Repeat for each column of each row of the shapes
				for (int colOffset = 0; colOffset < shape.getWidth(); colOffset++) {
					// System.out.println("ColOffset: " + colOffset);

					// If any row of the shape reaches the bottom then stop
					if (rowOffset + row == this.rows) {
						return false;
					}

					// If the next space is occupied then stop
					if (isOccupied(rowOffset + row, col + colOffset)) {

						// Check if the shape wants to occupy that space
						if (shape.isOccupied(rowOffset, colOffset)) {

							// Check that the shape is not what is currently
							// occupying the space
							if (!shape.isOccupied(rowOffset + 1, colOffset)) {
								return false;
							}
						}
					}
				}
			}
		}

		return true;
	}

}
