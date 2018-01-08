package amgg;

import java.math.*;
public class FieldMath {
	static double angleToPoint(double x1,double y1,double x2,double y2,double angle1){
		double angle = Math.atan2((y2-y1),(x2-x1));
		return (bestAngle(angle1,Math.toDegrees(angle)));
	}
	static double wrapAngle(double angle){
		while(angle>360){
			angle-=360;
		}
		while(angle<0){
			angle+=360;
		}
		return angle;
	}
	static double bestAngle(double botAngle,double targetAngle){
		double angle = (360-botAngle)+targetAngle;
		angle = wrapAngle(angle);
		if(angle>180){
			angle = -1*(360-angle);
		}
		return angle;
	}
	

}
