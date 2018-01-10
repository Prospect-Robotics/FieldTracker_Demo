import amgg.FieldTracker;

public class TestInterface{
	public static void main(String[] args) {
		FieldTracker robot = new FieldTracker(180, 280, 90, 20, 30,true);
		robot.setupGameField();
		robot.draw();
	}
}