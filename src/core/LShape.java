package core;

public class LShape extends Shape {

	public LShape() {
		super(3, 2);
		this.addBlock(0, 0);
		this.addBlock(0, 1);
		this.addBlock(0, 2);
		this.addBlock(1, 2);
	}
	
	public Shape reflection() {
		this.removeBlock(1, 2);
		this.addBlock(1, 0);
		return this;
	}

}
