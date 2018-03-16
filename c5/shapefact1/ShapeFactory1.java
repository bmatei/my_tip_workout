package c5.shapefact1;

import c5.badshape.BadShapeCreation;

import java.util.*;
import matei.utils.UnitTest;


abstract class Shape {
	public abstract void draw();
	public abstract void erase();

	public static Shape factory(String type)
	throws BadShapeCreation {
		if(type.equals("Circle")) {
			return new Circle();
		}
		if(type.equals("Square")) {
			return new Square();
		}
		throw new BadShapeCreation(type);
	}

}

class Circle extends Shape {
	Circle() {} //Friendly constructor
	public void draw() {
		System.out.println("Circle.draw");
	}
	public void erase() {
		System.out.println("Circle.erase");
	}
}

class Square extends Shape {
	Square() {} //Friendly constructor
	public void draw() {
		System.out.println("Square.draw");
	}
	public void erase() {
		System.out.println("Square.erase");
	}
}

public class ShapeFactory1 extends UnitTest {
	String shlist[] = { "Circle", "Square",
		"Square", "Circle", "Circle", "Square" };
	List shapes = new ArrayList();
	public void testMakingShapes() {
		try {
			for(int i = 0; i < shlist.length; ++i) {
				shapes.add(Shape.factory(shlist[i]));
			}
		} catch (BadShapeCreation e) {
			e.printStackTrace(System.err);
			myAssert(false); //Fail the unit test
		}
		Iterator i = shapes.iterator();
		while(i.hasNext()) {
			Shape s = (Shape)i.next();
			s.draw();
			s.erase();
		}
	}
	public static void main(String[] args) {
		new ShapeFactory1().testMakingShapes();
	}
}