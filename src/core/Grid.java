package core;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	
	protected List<Row> grid;
	protected int rows;
	protected int cols;
	
	public Grid(int cols, int rows) {
		this.rows = rows;
		this.cols = cols;
		grid = new ArrayList<>();
		for(int i = 0; i < rows; i++) {
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

	public void removeBlock(int row, int col) {
		grid.get(row).removeBlock(col);
	}
	
	public boolean isOccupied(int row, int col) {
		return grid.get(row).isOccupied(col);
	}
	
	public List<Row> getAllRows() {
		return this.grid;
	}
	
	@Override
	public String toString() {
		String output = "";

		// for(int i = grid.size() - 1; i >= 0; i--) {
		// output += grid.get(i) + "\n";
		// }
		int rowNumber = 0;
		for (Row row : grid) {
			output += "#" + rowNumber + ") " + row + "\n";
			rowNumber++;
		}

		return output;
	}
	

}
