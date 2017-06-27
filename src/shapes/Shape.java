package shapes;

import java.util.ArrayList;
import java.util.List;

import core.Grid;
import core.Position;
import core.Row;

public abstract class Shape extends Grid {

	private int xPos, yPos;

	public Shape(int cols, int rows) {
		super(cols, rows);
	}

	@Deprecated
	public Shape reflection() {
		return this;
	}

	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}

	public int getXPosition() {
		return this.xPos;
	}

	public int getYPosition() {
		return this.yPos;
	}

	public List<Position> getOccupiedSpaces() {
		List<Position> positions = new ArrayList<>();
		for (Row row : this) {
			int rowNum = grid.indexOf(row);
			for (int col = 0; col < cols; col++) {
				if (isOccupied(row, col)) {
					positions.add(new Position(col, rowNum));
				}
			}
		}
		return positions;
	}

	public List<Position> getBottomEdge() {
		List<Position> edges = new ArrayList<>();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (isOccupied(row, col)) {
					if (!isOccupied(row - 1, col)) {
						edges.add(new Position(col, row));
					}
				}

				// Break once we have enough edges
				if (edges.size() == cols) {
					return edges;
				}
			}
		}
		return edges;
	}

	public List<Position> getLeftEdge() {
		List<Position> edges = new ArrayList<>();
		for (int col = 0; col < cols; col++) {
			for (int row = 0; row < rows; row++) {
				if (isOccupied(row, col)) {
					if (col == 0 || !isOccupied(row, col - 1)) {
						edges.add(new Position(col, row));
					}
				}

				// Break once we have enough edges
				if (edges.size() == rows) {
					return edges;
				}
			}
		}
		return edges;
	}

	public List<Position> getRightEdge() {
		List<Position> edges = new ArrayList<>();
		for (int col = cols - 1; col >= 0; col--) {
			for (int row = 0; row < rows; row++) {
				if (isOccupied(row, col)) {
					if (col == cols - 1 || !isOccupied(row, col + 1)) {
						edges.add(new Position(col, row));
					}
				}

				// Break once we have enough edges
				if (edges.size() == rows) {
					return edges;
				}
			}
		}
		return edges;
	}

	public void rotateLeft() {

		Grid newGrid = new Grid(rows, cols);
		int newRows = cols;
		int newCols = rows;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (isOccupied(row, col)) {
					newGrid.addBlock(col, rows - row - 1);
				}
			}
		}

		this.grid = newGrid.getAllRows();
		this.rows = newRows;
		this.cols = newCols;
	}

	public void rotateRight() {

		Grid newGrid = new Grid(rows, cols);
		int newRows = cols;
		int newCols = rows;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (isOccupied(row, col)) {
					newGrid.addBlock(cols - col - 1, row);
				}
			}
		}

		this.grid = newGrid.getAllRows();
		this.rows = newRows;
		this.cols = newCols;
	}

	@Override
	public boolean isOccupied(int row, int col) {
		if (row < 0) {
			return false;
		}
		return super.isOccupied(row, col);
	}

}
