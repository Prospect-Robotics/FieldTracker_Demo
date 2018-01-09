import amgg.FieldTracker;

public class TestInterface {
	public static void main(String[] args) {
		FieldTracker robot = new FieldTracker(0, 0, 0, 10, 10);
		System.out.println(robot.collides(0, 1, 4, 2));
	}
}