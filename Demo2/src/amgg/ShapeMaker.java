package amgg;

import java.awt.Point;
import java.awt.Shape;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class ShapeMaker {
	static ArrayList<Shape> fromfile(File file) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		try {
			Scanner in = new Scanner(file);
			while(in.hasNext()){
				ArrayList<Point.Double> pointslist = new ArrayList<Point.Double>();
				String line = in.nextLine();
				String[] points = line.split(" ");
				for(String str:points){
					String[] xy = str.split(",");
					Point.Double point = new Point.Double(Double.valueOf(xy[0]),Double.valueOf(xy[1]));
					pointslist.add(point);
				}
			}
		} catch (FileNotFoundException e) {
		}
		return null;
	}
}
