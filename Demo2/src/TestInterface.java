import amgg.FieldTracker;

public class TestInterface {

	public static void main(String[] args) {
		FieldTracker tracker = new FieldTracker(0,0,0);//new tracker with robot at (0,0) with an angle of 0
		System.out.println(tracker.getDistanceToPoint(5, 5));
		System.out.println(tracker.getAngleToPoint(5, 5));
		tracker.navigateToPoint(5, 5);
		System.out.println(tracker.getDistanceToPoint(5, 0));
		System.out.println(tracker.getAngleToPoint(5, 0));
		tracker.navigateToPoint(5, 0);
	}

}
