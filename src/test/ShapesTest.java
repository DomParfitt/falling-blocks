package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import core.Position;
import shapes.LShape;
import shapes.LineShape;
import shapes.Shape;

public class ShapesTest {

	Shape shape;
	List<Position> actualPositions;
	List<Position> expectedPositions;
	
	@Before
	public void setup() {
		expectedPositions = new ArrayList<>();
	}
	
	@Test
	public void lineShape() {
		shape = new LineShape();
		actualPositions = shape.getOccupiedSpaces();
		expectedPositions.add(new Position(0,0));
		expectedPositions.add(new Position(1,0));
		expectedPositions.add(new Position(2,0));
		expectedPositions.add(new Position(3,0));
		for(Position position : expectedPositions) {
			assertTrue(actualPositions.contains(position));
		}
	}
	
	@Test
	public void lineShapeRotated() {
		shape = new LineShape();
		shape.rotateLeft();
		actualPositions = shape.getOccupiedSpaces();
		
		expectedPositions.add(new Position(0,0));
		expectedPositions.add(new Position(0,1));
		expectedPositions.add(new Position(0,2));
		expectedPositions.add(new Position(0,3));
		for(Position position : expectedPositions) {
			assertTrue(actualPositions.contains(position));
		}
	}
	
	@Test
	public void lShape() {
		shape = new LShape();
		actualPositions = shape.getOccupiedSpaces();
		expectedPositions.add(new Position(0,0));
		expectedPositions.add(new Position(1,0));
		expectedPositions.add(new Position(2,0));
		expectedPositions.add(new Position(2,1));
		for(Position position : expectedPositions) {
			assertTrue(actualPositions.contains(position));
		}
	}
	
	@Test
	public void lShapeRotated90() {
		shape = new LShape();
		shape.rotateRight();
		actualPositions = shape.getOccupiedSpaces();
		expectedPositions.add(new Position(0,0));
		expectedPositions.add(new Position(1,0));
		expectedPositions.add(new Position(0,1));
		expectedPositions.add(new Position(0,2));
		for(Position position : expectedPositions) {
			assertTrue(actualPositions.contains(position));
		}
	}
	
	@Test
	public void lShapeRotated180() {
		shape = new LShape();
		shape.rotateLeft();
		shape.rotateLeft();
		actualPositions = shape.getOccupiedSpaces();
		expectedPositions.add(new Position(0,0));
		expectedPositions.add(new Position(0,1));
		expectedPositions.add(new Position(1,1));
		expectedPositions.add(new Position(2,1));
		for(Position position : expectedPositions) {
			assertTrue(actualPositions.contains(position));
		}
		
	}
	
	@Test
	public void lShapeRotated270() {
		shape = new LShape();
		shape.rotateLeft();
		actualPositions = shape.getOccupiedSpaces();
		for(Position position : actualPositions) {
			System.out.println(position);
		}
		expectedPositions.add(new Position(0,0));
		expectedPositions.add(new Position(0,1));
		expectedPositions.add(new Position(0,2));
		expectedPositions.add(new Position(1,0));
		for(Position position : expectedPositions) {
			assertTrue(actualPositions.contains(position));
		}
	}
	
	@Test
	public void lShapeReflected() {
		shape = new LShape();
		shape.reflection();
		actualPositions = shape.getOccupiedSpaces();
		expectedPositions.add(new Position(0,0));
		expectedPositions.add(new Position(1,0));
		expectedPositions.add(new Position(2,0));
		expectedPositions.add(new Position(0,1));
		for(Position position : expectedPositions) {
			assertTrue(actualPositions.contains(position));
		}
	}
	
	@Test
	public void lShapeReflectedRotated90() {
		shape = new LShape();
		shape.reflection();
		shape.rotateRight();
		actualPositions = shape.getOccupiedSpaces();
		expectedPositions.add(new Position(0,0));
		expectedPositions.add(new Position(1,0));
		expectedPositions.add(new Position(0,1));
		expectedPositions.add(new Position(0,2));
		for(Position position : expectedPositions) {
			assertTrue(actualPositions.contains(position));
		}
	}
	
	@Test
	public void lShapeReflectedRotated180() {
		shape = new LShape();
		shape.reflection();
		shape.rotateLeft();
		shape.rotateLeft();
		actualPositions = shape.getOccupiedSpaces();
		expectedPositions.add(new Position(0,0));
		expectedPositions.add(new Position(0,1));
		expectedPositions.add(new Position(1,1));
		expectedPositions.add(new Position(1,2));
		for(Position position : expectedPositions) {
			assertTrue(actualPositions.contains(position));
		}
	}
	
	@Test
	public void lShapeReflectedRotated270() {
		shape = new LShape();
		shape.reflection();
		shape.rotateLeft();
		actualPositions = shape.getOccupiedSpaces();
		expectedPositions.add(new Position(0,0));
		expectedPositions.add(new Position(1,0));
		expectedPositions.add(new Position(1,1));
		expectedPositions.add(new Position(1,2));
		for(Position position : expectedPositions) {
			assertTrue(actualPositions.contains(position));
		}
	}

}
