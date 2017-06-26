package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a row for the falling blocks game
 * @author Dom Parfitt
 *
 */
public class Row {
	
	public static long ROW_COUNT = 0;
	
	private long rowId;
	private List<Boolean> cells;
	private int size;
	
	/**
	 * Initialise a row of a given size
	 * @param size the number of cells in the row
	 */
	public Row(int size) {
		ROW_COUNT++;
		this.rowId = ROW_COUNT;
		
		this.size = size;
		cells = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			cells.add(false);
		}
	}
	
	/**
	 * Get the unique ID of the row
	 * @return the unique row ID
	 */
	public long getId() {
		return this.rowId;
	}
	
	/**
	 * Get the number of cells in the row
	 * @return the number of cells
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Checks whether a row is complete
	 * @return true if the row is complete, false otherwise
	 */
	public boolean isComplete() {
		
		//Loop through all entries, if any are false return false
		for(Boolean cell : cells) {
			if(!cell) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Check whether a particular column is occupied
	 * @param col the column to check
	 * @return true if the cell is occupied, false otherwise
	 */
	public boolean isOccupied(int col) {
		return cells.get(col);
	}
	
	/**
	 * Adds a block at a given column
	 * @param colNum the column to add the block
	 */
	public void addBlock(int colNum) {
		cells.set(colNum, true);
	}
	
	/**
	 * Removes a block from a given column
	 * @param colNum the column to remove the block from
	 */
	public void removeBlock(int colNum) {
		cells.set(colNum, false);
	}
	
	@Override
	public String toString() {
		String output = "";
		for(Boolean cell : cells) {
			if(cell) {
				output += " 1 ";
			} else {
				output += " 0 ";
			}
		}
		
		return output;
	}
	
	/**
	 * Compares two Rows for equality
	 * @param other the other Row
	 * @return true if the Rows have the same ID, false otherwise
	 */
	public boolean equals(Row other) {
		return this.getId() == other.getId();
	}
}
