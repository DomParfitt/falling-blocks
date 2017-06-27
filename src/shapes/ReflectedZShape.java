package shapes;

public class ReflectedZShape extends Shape {
	
	public ReflectedZShape() {
		super(3, 2);
		this.addBlock(1, 0);
		this.addBlock(0, 1);
		this.addBlock(1, 1);
		this.addBlock(0, 2);
	}

}
