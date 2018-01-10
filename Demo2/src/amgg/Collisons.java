package amgg;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.*;

class Collisions {
	static boolean doShapesCollide(Shape shape1, Shape shape2) {
		Area a1 = new Area(shape1);
		Area a2 = new Area(shape2);
		a1.intersect(a2);
		return !a1.isEmpty();
	}
	
	static boolean isShapeInside(Shape shape,Shape bounds){
		Area a = new Area(shape);
		Area b = new Area(bounds);
		b.intersect(a);
		return b.equals(a);
	}
	
	static Shape makeMovementHitbox(Point.Double destination) {
		double l = FieldTracker.robotLength;
		double w = FieldTracker.robotWidth;
		double x = FieldTracker.robotPosition.getX();
		double y = FieldTracker.robotPosition.getY();
		Point.Double tl = new Point.Double(x - l / 2, y + w / 2);// top left
		Point.Double tr = new Point.Double(x + l / 2, y + w / 2);// top right
		Point.Double bl = new Point.Double(x - l / 2, y - w / 2);// bottom left
		Point.Double br = new Point.Double(x + l / 2, y - w / 2);// bottom right
		for (Point.Double point : new Point.Double[] { tl, tr, bl, br }) {
			PointMath.rotate(point, FieldTracker.robotPosition, FieldTracker.getAngleToPoint(destination));
		}
		for (Point.Double point : new Point.Double[] { tl, tr }) {
			PointMath.moveAlong(point, FieldTracker.getDistanceToPoint(destination),
					FieldTracker.getAngleToPoint(destination));
		}
		Path2D.Double path = new Path2D.Double();
		path.moveTo(tl.getX(), tl.getY());
		for (Point.Double point : new Point.Double[] { tr, bl, br }) {
			path.moveTo(point.getX(), point.getY());
		}
		path.closePath();
		return path;
	}
}