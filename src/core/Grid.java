package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Grid extends Observable implements Iterable<Row> {

	protected List<Row> grid;
	protected int rows;
	protected int cols;

	public Grid(int cols, int rows) {
		this.rows = rows;
		this.cols = cols;
		grid = new ArrayList<>();
		for (int i = 0; i < rows; i++) {
			grid.add(new Row(cols));
		}
	}

	public int getWidth() {
		return cols;
	}

	public int getHeight() {
		return rows;
	}

	public Row getRow(int row) {
		return grid.get(row);
	}

	public void addRow(Row row) {
		grid.add(row);
	}

	public void removeRow(Row row) {
		grid.remove(row);
	}

	public void addBlock(int row, int col) {
		grid.get(row).addBlock(col);
	}
	
	public void addBlock(Row row, int col) {
		addBlock(grid.indexOf(row), col);
	}

	public void removeBlock(int row, int col) {
		grid.get(row).removeBlock(col);
	}
	
	public void removeBlock(Row row, int col) {
		removeBlock(grid.indexOf(row), col);
	}

	public boolean isOccupied(int row, int col) {
		if (row < 0 || col < 0 || row >= rows) {
			return true;
		}
		return grid.get(row).isOccupied(col);
	}
	
	public boolean isOccupied(Row row, int col) {
		
		return isOccupied(grid.indexOf(row), col);
	}

	public List<Row> getAllRows() {
		return this.grid;
	}

	@Override
	public String toString() {
		String output = "";
		
		for(Row row : this) {
			output += "#" + row.getId() + ") " + row + "\n";
		}

		return output;
	}

	@Override
	public Iterator<Row> iterator() {
		return new Iterator<Row>() {

			private int count = grid.size();
			
			@Override
			public boolean hasNext() {
				return count > 0;
			}

			@Override
			public Row next() {
				Row row = grid.get(--count);
				return row;
			}
		};
	}

}
