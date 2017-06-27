package shapes;

public class SquareShape extends Shape {

	public SquareShape() {
		super(2, 2);
		addBlock(0, 0);
		addBlock(0, 1);
		addBlock(1, 0);
		addBlock(1, 1);
	}

}
