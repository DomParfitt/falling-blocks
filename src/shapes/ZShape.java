package shapes;

public class ZShape extends Shape {

	public ZShape() {
		super(3, 2);
		this.addBlock(0, 0);
//		System.out.println(this);
		this.addBlock(0, 1);
//		System.out.println(this);
		this.addBlock(1, 1);
//		System.out.println(this);
		this.addBlock(1, 2);
//		System.out.println(this);
	}
	
	public Shape reflection() {
		this.removeBlock(0, 0);
		this.removeBlock(1, 2);
		this.addBlock(1, 0);
		this.addBlock(0, 2);
		return this;
	}

}
