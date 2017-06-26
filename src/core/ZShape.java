package core;

public class ZShape extends Shape {

	public ZShape() {
		super(3, 2);
		this.addBlock(0, 0);
		this.addBlock(0, 1);
		this.addBlock(1, 1);
		this.addBlock(1, 2);
	}
	
	public Shape reflection() {
		this.removeBlock(0, 0);
		this.removeBlock(1, 2);
		this.addBlock(1, 0);
		this.addBlock(0, 2);
		return this;
	}

}
