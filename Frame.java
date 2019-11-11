import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

/**
 * Displays the entire game inside JFrame.
 */
public class Frame extends JFrame {

	public Frame() {

		JFrame frame = new JFrame("Whack-A-Mole");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set frame size to computer's screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);


		final SceneComponent scene = new SceneComponent();

		// Generates random x and y coordinate values for Holes
		int a = (int) (Math.random() * (450 - 100)) + 100;
		int b = (int) (Math.random() * (450 - 50) ) + 60;
		int c = (int) (Math.random() * (900 - 600)) + 600;
		int d = (int) (Math.random() * (400 - 100) ) + 100;
		int e = (int) (Math.random() * (400 - 100) ) + 100;
		int f = (int) (Math.random() * (900 - 600)) + 600;
		int g = (int) (Math.random() * (900 - 600)) + 600;
		int h = (int) (Math.random() * (900 - 600)) + 600;
		int i = (int) (Math.random() * (750 - 450)) + 450;
		int j = (int) (Math.random() * (750 - 450)) + 450;

		// Creates 5 Holes and Mole and adds them to the scene
		final Hole hole = new Hole(a, b, 0, 0);
		final Hole hole2 = new Hole(c, d, 0, 0);
		final Hole hole3 = new Hole(e, f, 0, 0);
		final Hole hole4 = new Hole(g, h, 0, 0);
		final Hole hole5 = new Hole(i, j, 0, 0);
		final Mole mole = new Mole(250, 300, 0, 0);
		scene.add(hole);
		scene.add(hole2);
		scene.add(hole3);
		scene.add(hole4);
		scene.add(hole5);
		scene.add(mole);

		// Changes default Windows cursor to custom hammer image
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("src\\hammer.png");
        Image scaledImage = image.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		Cursor cursor = toolkit.createCustomCursor(scaledImage , new Point(frame.getX(), frame.getY()), "hammer");

        frame.add(scene);
        frame.getContentPane().setBackground(Color.GREEN);
		frame.setCursor(cursor);
		frame.setVisible(true);

		// Remaining code below excluding main is for testing purposes. To be removed later.
		final int DELAY = 5;
		Timer t = new Timer(DELAY, event ->
		{
			scene.repaint();
		});
		hole.addAnimateTimer(t);
		hole2.addAnimateTimer(t);
		hole3.addAnimateTimer(t);
		hole4.addAnimateTimer(t);
		hole5.addAnimateTimer(t);
		mole.addAnimateTimer(t);
		t.start();


		ArrayList<Integer> xCoord = new ArrayList<>();
		xCoord.add(a);
		xCoord.add(c);
		xCoord.add(e);
		xCoord.add(g);
		xCoord.add(i);

		Map<Integer, Integer> XtoY = new HashMap<>();

		ArrayList<Integer> yCoord = new ArrayList<>();
		yCoord.add(b);
		yCoord.add(d);
		yCoord.add(f);
		yCoord.add(h);
		yCoord.add(j);

		// Note: this is just a demo to show that every time you call GrowableShape.animate(), it performs one full
		// grow/shrink animation
		Timer animator = new Timer(3000, animationEvent ->
		{
			// If any Hole shrinks down to 0 (board is clear and has no Holes), find a new random x and y to respawn
			if (hole.getWidth() == 0) {
				//top left circle
				hole.setX((int) (Math.random() * (450 - 100)) + 100);
				hole.setY((int) (Math.random() * (450 - 50)) + 60);

				//top right circle
				hole2.setX((int) (Math.random() * (900 - 600)) + 600);
				hole2.setY((int) (Math.random() * (400 - 100)) + 100);

				//bottom left circle
				hole3.setX((int) (Math.random() * (400 - 100)) + 100);
				hole3.setY((int) (Math.random() * (900 - 600)) + 600);

				//bottom right circle
				hole4.setX((int) (Math.random() * (900 - 600)) + 600);
				hole4.setY((int) (Math.random() * (900 - 600)) + 600);

				//middle hole, 5 holes total. This one will need editing to avoid overlapping
				hole5.setX((int) (Math.random() * (750 - 450)) + 450);
				hole5.setY((int) (Math.random() * (750 - 450)) + 450);

				mole.setX((int) (Math.random() * (450 - 100)) + 100);
			}

			hole.animate();
			hole2.animate();
			hole3.animate();
			hole4.animate();
			hole5.animate();
			mole.animate();
		}
		);
		animator.start();
	}

	public static void main(String[] args) {
		Frame x = new Frame();
		Audio a = new Audio("src\\GameMusic.wav");
		a.play();
	}
}