package amgg;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 * @author amgg
 * @author Dragon Controller
 */
public class FieldTracker {
	static Point.Double robotPosition;
	static double robotAngle;
	static double robotWidth;
	static double robotLength;
	static ArrayList<Shape> obstacles = new ArrayList<Shape>();
	/**
	 * @param robotX
	 *            - starting x coordinate of robot
	 * @param robotY
	 *            - starting y coordinate of robot
	 * @param robotAngle
	 *            - starting angle of robot
	 */
	public FieldTracker(double robotX, double robotY, double robotAngle, double robotWidth, double robotLength) {
		FieldTracker.robotPosition = new Point.Double(robotX,robotY);
		FieldTracker.robotAngle = robotAngle;
		FieldTracker.robotWidth = robotWidth;
		FieldTracker.robotLength = robotLength;
	}

	/**
	 * @param angle
	 *            - update the robot's angle in the tracker by this amount
	 */
	public void turnedRobot(double angle) {
		robotAngle = FieldMath.wrapAngle(robotAngle + angle);
	}
	
	public void setRobotPos(Point.Double newpos){
		setRobotPos(newpos.getX(),newpos.getY());
	}
	public void setRobotPos(double x,double y){
		FieldTracker.robotPosition.setLocation(x,y);
	}

	/**
	 * @param distance
	 *            - move the robot forwards in the tracker by this amount
	 */
	public void movedRobot(double distance) {
		double sin = Math.sin(Math.toRadians(robotAngle));
		double cos = Math.cos(Math.toRadians(robotAngle));
		PointMath.moveAlong(robotPosition, distance, robotAngle);
	}

	/**
	 * @param x
	 * @param y
	 * @return - angle by which robot needs to turn
	 */
	public static double getAngleToPoint(double x, double y) {
		return FieldMath.adjustedAngleToPoint(robotPosition.getX(), robotPosition.getY(), x, y, robotAngle);
	}
	public static double getAngleToPoint(Point.Double point) {
		return getAngleToPoint(point.getX(),point.getY());
	}

	/**
	 * @param x
	 * @param y
	 * @return - distance by which to move robot
	 */
	public static double getDistanceToPoint(double x, double y) {
		return getDistanceToPoint(new Point.Double(x,y));
	}
	public static double getDistanceToPoint(Point.Double point){
		return robotPosition.distance(point);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void navigateToPoint(double x, double y) {
		turnedRobot(getAngleToPoint(x, y));
		movedRobot(getDistanceToPoint(x, y));
	}
	
	public void createObstacle(Point.Double[] points){
		if(points.length>2){
			Path2D.Double path = new Path2D.Double();
			path.moveTo(points[0].getX(),points[0].getY());
			for(int i=1;i<points.length;i++){
				path.lineTo(points[i].getX(),points[i].getY());
			}
			path.closePath();
			createObstacle(path);
		}
	}
	public void createObstacle(Shape obstacle){
		obstacles.add(obstacle);
	}
	

	/*public boolean collides(double newX, double newY, double objectX, double objectY) {
		double angle = Math.atan((newY - robotY / newX - robotX));
		if (newX - robotX == 0) {
			angle = Math.PI / 2 * Math.abs(newY - robotY) / (newY - robotY);
		}
		double angle2 = Math.atan2(robotLength, robotWidth);
		double distance = Math.hypot(robotWidth, robotLength) / 2;
		double ax = robotX - distance * Math.abs(Math.sin(angle + angle2));
		double ay = robotY - distance * Math.abs(Math.cos(angle + angle2));
		double bx = robotX + distance * Math.abs(Math.sin(angle - angle2));
		double by = robotY - distance * Math.abs(Math.cos(angle - angle2));
		double cx = newX - distance * Math.abs(Math.sin(angle - angle2));
		double cy = newY + distance * Math.abs(Math.cos(angle - angle2));
		double dx = newX + distance * Math.abs(Math.sin(angle + angle2));
		double dy = newY + distance * Math.abs(Math.cos(angle + angle2));
		double area = area(ax, ay, bx, by, objectX, objectY) + area(bx, by, dx, dy, objectX, objectY)
				+ area(dx, dy, cx, cy, objectX, objectY) + area(cx, cy, ax, ay, objectX, objectY);
		return Math.round(area) == robotWidth * (robotLength + Math.hypot(newY - robotY, newX - robotX));
	}

	public double area(double ax, double ay, double bx, double by, double cx, double cy) {
		return Math.abs((ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) / 2);
	}*/
}
