package amgg;

/**
 * @author amgg
 * @author Dragon Controller
 */
public class FieldTracker {
	double robotX;
	double robotY;
	double robotAngle;
	double robotWidth;
	double robotHeight;

	/**
	 * @param robotX
	 *            - starting x coordinate of robot
	 * @param robotY
	 *            - starting y coordinate of robot
	 * @param robotAngle
	 *            - starting angle of robot
	 */
	public FieldTracker(double robotX, double robotY, double robotAngle, double robotWidth, double robotHeight) {
		this.robotX = robotX;
		this.robotY = robotY;
		this.robotAngle = robotAngle;
		this.robotWidth = robotWidth;
		this.robotHeight = robotHeight;
	}

	/**
	 * @param angle
	 *            - update the robot's angle in the tracker by this amount
	 */
	public void turnedRobot(double angle) {
		robotAngle = FieldMath.wrapAngle(robotAngle + angle);
	}

	/**
	 * @param distance
	 *            - move the robot forwards in the tracker by this amount
	 */
	public void movedRobot(double distance) {
		double sin = Math.sin(Math.toRadians(robotAngle));
		double cos = Math.cos(Math.toRadians(robotAngle));
		robotX += sin * distance;
		robotY += cos * distance;
	}

	/**
	 * @param x
	 * @param y
	 * @return - angle by which robot needs to turn
	 */
	public double getAngleToPoint(double x, double y) {
		return FieldMath.angleToPoint(robotX, robotY, x, y, robotAngle);
	}

	/**
	 * @param x
	 * @param y
	 * @return - distance by which to move robot
	 */
	public double getDistanceToPoint(double x, double y) {
		return Math.sqrt(Math.pow((robotY - y), 2) + Math.pow((robotX - x), 2));
	}

	/**
	 * @param x
	 * @param y
	 */
	public void navigateToPoint(double x, double y) {
		turnedRobot(getAngleToPoint(x, y));
		movedRobot(getDistanceToPoint(x, y));
	}

	public boolean collides(double newX, double newY, double objectX, double objectY) {
		double angle = Math.atan((newY - robotY / newX - robotX));
		if (newX - robotX == 0) {
			angle = Math.PI / 2 * Math.abs(newY - robotY) / (newY - robotY);
		}
		double angle2 = Math.atan2(robotHeight, robotWidth);
		double distance = Math.hypot(robotWidth, robotHeight) / 2;
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
		return Math.round(area) == robotWidth * (robotHeight + Math.hypot(newY - robotY, newX - robotX));
	}

	public double area(double ax, double ay, double bx, double by, double cx, double cy) {
		return Math.abs((ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) / 2);
	}
}
