package amgg;

/**
 * @author amgg
 *
 */
public class FieldTracker {
	double robotX;
	double robotY;
	double robotAngle;
	
	/**
	 * @param robotX - starting x coordinate of robot
	 * @param robotY - starting y coordinate of robot
	 * @param robotAngle - starting angle of robot
	 */
	public FieldTracker(double robotX, double robotY, double robotAngle) {
		this.robotX = robotX;
		this.robotY = robotY;
		this.robotAngle = robotAngle;
	}

	/**
	 * @param angle - update the robot's angle in the tracker by this amount
	 */
	public void turnedRobot(double angle) {
		robotAngle = FieldMath.wrapAngle(robotAngle + angle);
	}

	/**
	 * @param distance - move the robot forwards in the tracker by this amount
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
	public void navigateToPoint(double x,double y){
		turnedRobot(getAngleToPoint(x,y));
		movedRobot(getDistanceToPoint(x,y));
	}
}
