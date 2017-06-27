package shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Factory class for providing Shape objects to the Game on request
 * 
 * @author Dom Parfitt
 *
 */
public class ShapeFactory {

	private List<Shape> shapes;
	private Random rand;
	private int zCount = 0;

	/**
	 * Default constructor
	 */
	public ShapeFactory() {
		this.shapes = new ArrayList<>();
		shapes.add(new LShape());
		shapes.add(new ReflectedLShape());
		shapes.add(new TShape());
		shapes.add(new ZShape());
		shapes.add(new ReflectedZShape());
		shapes.add(new LineShape());
		shapes.add(new SquareShape());
		this.rand = new Random();
	}

	/**
	 * Construct a ShapeFactory with a list of Shapes
	 * @param shapes a list of Shapes
	 */
	public ShapeFactory(List<Shape> shapes) {
		this.shapes = shapes;
		this.rand = new Random();
	}

	/**
	 * Provides a shape to the requester
	 * @return a randomly selected Shape which may have been
	 * 			reflected and/or rotated
	 */
	public Shape getShape() {

		// Randomly select a shape from the list
		Shape shape = shapes.get(rand.nextInt(shapes.size()));
		
		if(shape.getClass().equals(ZShape.class)) {
			System.out.println("Z-Count: " +  ++zCount);
		}

		// Randomly determine whether the shape should be reflected
//		if (rand.nextBoolean()) {
//			shape = shape.reflection();
//		}

		//Determine whether shape should be rotated
		if(rand.nextBoolean()) {
			
			//Determine how many rotations to perform
			int rotations = rand.nextInt(2) + 1;
			for(int i = 0; i < rotations; i++) {
				shape.rotateRight();
			}
		}

		return shape;
	}

}
