package amgg;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.*;
import java.math.*;
class Collisons {
	static boolean doShapesCollide(Shape shape1,Shape shape2){
		Area a1 = new Area(shape1);
		Area a2 = new Area(shape2);
		a1.intersect(a2);
		return !a1.isEmpty();
	}
}