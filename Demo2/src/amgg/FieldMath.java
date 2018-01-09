package amgg;

public class FieldMath {
	static double angleToPoint(double x1, double y1, double x2, double y2, double angle1) {
		return bestAngle(angle1, Math.toDegrees(Math.atan2((y2 - y1), (x2 - x1))));
	}

	static double wrapAngle(double angle) {
		angle %= 360;
		if (angle < 0) {
			angle += 360;
		}
		return angle;
	}

	static double bestAngle(double botAngle, double targetAngle) {
		double angle = wrapAngle(targetAngle - botAngle);
		if (angle > 180) {
			angle -= 360;
		}
		return angle;
	}

}
