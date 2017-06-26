package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShapeFactory {
	
	private List<Shape> shapes;
	private Random rand;
	
	public ShapeFactory() {
		this.shapes = new ArrayList<>();
		shapes.add(new LShape());
		shapes.add(new TShape());
		shapes.add(new ZShape());
		this.rand = new Random();
	}
	
	public ShapeFactory(List<Shape> shapes) {
		this.shapes = shapes;
		this.rand = new Random();
	}
	
	public Shape getShape() {
		Shape shape = shapes.get(rand.nextInt(shapes.size()));
		
		if(rand.nextBoolean()) {
			shape = shape.reflection();
		}
		
		int rotate = rand.nextInt(2);
		
		if(rotate > 0) {
			
			int rotations = rand.nextInt(2) + 1;
			if (rotate == 1) {
				
			} else {
				for(int i = 0; i < rotations; i++) {
					shape.rotateRight();
				}
			}
		}
		
		return shape;
	}

}
