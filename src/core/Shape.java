package core;

public abstract class Shape extends Grid {

	public Shape(int cols, int rows) {
		super(cols, rows);
	}
	
	public Shape reflection() {
		return this;
	}
	
	public void rotateRight() {
		
		Grid newGrid = new Grid(rows, cols);
		int newRows = cols;
		int newCols = rows;
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if(isOccupied(row, col)) {
					newGrid.addBlock(col, rows - row - 1);
				}
			}
		}
		
		this.grid = newGrid.getAllRows();
		this.rows = newRows;
		this.cols = newCols;
	}
	
	public void rotateLeft() {
		
		Grid newGrid = new Grid(rows, cols);
		int newRows = cols;
		int newCols = rows;
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if(isOccupied(row, col)) {
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
		if(row >= rows) {
			return false;
		}
		return super.isOccupied(row, col);
	}
	

}
