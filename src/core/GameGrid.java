package core;

import java.util.ArrayList;
import java.util.List;

import shapes.Shape;

public class GameGrid extends Grid {

	public GameGrid(int cols, int rows) {
		super(cols, rows);
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
	public void dropBlocks(int col, int numBlocks) {
		for (int row = 0; row < rows; row++) {
			for (int i = 0; i < numBlocks; i++) {
				if (isOccupied(row, col + i)) {
					return;
				}
				addBlock(row, col + i);
				if (row > 0) {
					removeBlock(row - 1, col + i);
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
	public void dropGrid(int col, int width, int height) {
		for (int row = 0; row < rows; row++) {
			// for (int gridRow = height - 1; gridRow >= 0; gridRow--) {
			for (int gridRow = 0; gridRow < height; gridRow++) {
				for (int i = 0; i < width; i++) {
					if (row - gridRow >= 0) {
						if (isOccupied(row - gridRow, col + i)) {
							return;
						}
						addBlock(row - gridRow, col + i);
						if (row - gridRow > 0) {
							removeBlock(row - gridRow - 1, col + i);
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
	//TODO: It's clunky having this be Observable, split this method out into
	// 		a single downwards movement that must be called repeatedly (this
	//		should also make it easier to implement controlling block)
	public synchronized int dropShape(int col, Shape shape) {
		int returnRow = 0;
		
		// Repeat for every row in the grid
		// Starts from an imaginary row to accommodate the whole shape
		for (int row = 0 - shape.getHeight() + 1; row < this.rows; row++) {
			returnRow = row;
			if (canProgress(row, col, shape)) {
				// Repeat for each row in the shape
				for (int gridRow = shape.getHeight() - 1; gridRow >= 0; gridRow--) {

					// Repeat for each column of each row of the shapes
					for (int gridCol = 0; gridCol < shape.getWidth(); gridCol++) {

						//
						if (gridRow + row >= 0) {

							// Moves the shape downwards
							if (shape.isOccupied(gridRow, gridCol)) {
								// System.out.println("Shape is occupied");
								addBlock(gridRow + row, col + gridCol);
							}

							// Removes the blocks from where the shape was
							if (gridRow + row > 0) {
								removeBlock(gridRow + row - 1, col + gridCol);
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

	private boolean canProgress(int row, int col, Shape shape) {
		if (row >= 0) {
			for (int rowOffset = shape.getHeight() - 1; rowOffset >= 0; rowOffset--) {
//				System.out.println("RowOffset: " + rowOffset);
				// Repeat for each column of each row of the shapes
				for (int colOffset = 0; colOffset < shape.getWidth(); colOffset++) {
//					System.out.println("ColOffset: " + colOffset);

					// If any row of the shape reaches the bottom then stop
					if (rowOffset + row == this.rows) {
						return false;
					}

					// If the next space is occupied then stop
					if (isOccupied(rowOffset + row, col + colOffset)) {
						
						//Check if the shape wants to occupy that space
						if (shape.isOccupied(rowOffset, colOffset)) {
							
							//Check that the shape is not what is currently occupying the space
							if(!shape.isOccupied(rowOffset + 1, colOffset)) {
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
