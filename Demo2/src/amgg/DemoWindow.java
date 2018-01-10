package amgg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class DemoWindow implements MouseListener, MouseMotionListener, KeyListener {
	static JLabel label;
	static JDialog dialog;
	static BufferedImage img;
	Graphics2D g2d;

	public DemoWindow() {
		img = new BufferedImage(888, 360, BufferedImage.TYPE_INT_ARGB);
		label = new JLabel(new ImageIcon(img));
		dialog = new JDialog();
		dialog.add(label);
		dialog.pack();
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		g2d = img.createGraphics();
		label.addMouseListener(this);
		label.addMouseMotionListener(this);
		dialog.addKeyListener(this);
		FieldTracker.g2d = g2d;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX() + "," + e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()){
		case 'w':
			System.out.println("w");
			FieldTracker.movedRobot(4);
			FieldTracker.draw();
			break;
		case 's':
			System.out.println("s");
			FieldTracker.movedRobot(-4);
			FieldTracker.draw();
			break;
		case 'a':
			System.out.println("a");
			FieldTracker.turnedRobot(-2);
			FieldTracker.draw();
			break;
		case 'd':
			System.out.println("d");
			FieldTracker.turnedRobot(2);
			FieldTracker.draw();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
