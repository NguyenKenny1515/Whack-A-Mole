
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Displays the entire game inside JFrame.
 */
public class Frame extends JFrame {
	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		public int screenWidth = screenSize.width;
		public int screenHeight = screenSize.height;
	
	public Frame() {
		
		JFrame frame = new JFrame("Whack-a-mole");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final SceneComponent scene = new SceneComponent();
		JOptionPane.showMessageDialog(null, "Press Ok To Start","Start", JOptionPane.INFORMATION_MESSAGE);
		
		frame.setSize(screenWidth, screenHeight);
	
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("src\\background.png"));
		background.setBounds(0,0,screenWidth,screenHeight);
	

		//frame.setResizable(false);
		

		// 8 Holes total, in a checkered pattern. The original (first) appearance (we want the screen to be blank at first,
		//but we have to declare the holes to be somewhere on the screen
		
		//Set the X value to -100 so it animates out of screen so no holes are first seen
		//top left
		final Hole hole = new Hole(-100, 0, 0, 0);
		scene.add(hole);
		
		//top middle
		final Hole hole1 = new Hole(-100, 0, 0, 0);
		scene.add(hole1);
		
		//top right
		final Hole hole2 = new Hole(-100, 0, 0, 0);
		scene.add(hole2);
		
		//middle left
		final Hole hole3 = new Hole(-100, 0, 0, 0);
		scene.add(hole3);
		//middle right
		final Hole hole4 = new Hole(-100, 0, 0, 0);
		scene.add(hole4);
		//bottom left
		final Hole hole5 = new Hole(-100, 0, 0, 0);
		scene.add(hole5);
		//bottom middle
		final Hole hole6 = new Hole(-100, 0, 0, 0);
		scene.add(hole6);

		//the mole
		Mole mole = new Mole(-100, 0, 0, 0);
		scene.add(mole);

		frame.add(scene);


		frame.setVisible(true);
		
		
		frame.add(background);

		// hard = 0, medium = 5, easy = 10
		final int DELAY = 0;
		// Milliseconds between timer ticks
		Timer t = new Timer(DELAY, event -> {
			scene.repaint();
		});

		hole.addAnimateTimer(t);
		hole1.addAnimateTimer(t);
		hole2.addAnimateTimer(t);
		hole3.addAnimateTimer(t);
		hole4.addAnimateTimer(t);
		hole5.addAnimateTimer(t);
		hole6.addAnimateTimer(t);

		mole.addAnimateTimer(t);
		t.start();

		ArrayList<Hole> holes = new ArrayList<Hole>();

		// Note: this is just a demo to show that every time you call
		// GrowableShape.animate(), it performs one full grow/shrink animation
		Timer animator = new Timer(1000, animationEvent -> {
			// if any of the holes shrink down to 0, find a new random x and y to respawn
			if (hole.getWidth() == 0) {
				
				// hole 1 - top left Math.random() * (maximum - minimum) + 100)
				// X coordinate
				int q = (int) (Math.random() * (screenWidth / 5 - 100)) + 100;
				// Y coordinate
				int q1 = (int) (Math.random() * (screenHeight / 3 - 50)) + 60;

				// hole 2 - top middle
				// x coordinate
				int q2 = (int) (Math.random() * (screenWidth * 3 / 5 - screenWidth * 2 / 5)) + screenWidth * 2 / 5;
				// y coordinate
				int q3 = (int) (Math.random() * (screenHeight / 3 - 100)) + 100;

				// hole 3 top right
				int q4 = (int) (Math.random() * ((screenWidth - 100) - screenWidth * 4 / 5)) + screenWidth * 4 / 5;
				int q5 = (int) (Math.random() * (screenHeight / 3 - 100)) + 100;

				// hole 4 left middle
				int q6 = (int) (Math.random() * (screenWidth * 2 / 5 - screenWidth / 5)) + screenWidth / 5;
				int q7 = (int) (Math.random() * (screenHeight * 2 / 3 - screenHeight / 3)) + screenHeight / 3;

				// hole5 right middle
				int q8 = (int) (Math.random() * (screenWidth * 4 / 5 - screenWidth * 3 / 5)) + screenWidth * 3 / 5;
				int q9 = (int) (Math.random() * (screenHeight * 2 / 3 - screenHeight / 3)) + screenHeight / 3;

				// hole6 bottom left
				int q10 = (int) (Math.random() * (screenWidth / 5 - 100)) + 100;
				int q11 = (int) (Math.random() * ((screenHeight - 100) - screenHeight * 2 / 3)) + screenHeight * 2 / 3;

				// hole7 bottom middle
				int q12 = (int) (Math.random() * (screenWidth * 3 / 5 - screenWidth * 2 / 5)) + screenWidth * 2 / 5;
				int q13 = (int) (Math.random() * ((screenHeight - 100) - screenHeight * 2 / 3)) + screenHeight * 2 / 3;

				// top left circle
				hole.x = q;
				hole.y = q1;

				// top middle circle
				hole1.x = q2;
				hole1.y = q3;

				// top right circle
				hole2.x = q4;
				hole2.y = q5;

				// middle left circle
				hole3.x = q6;
				hole3.y = q7;

				// middle right circle
				hole4.x = q8;
				hole4.y = q9;

				// bottom left circle
				hole5.x = q10;
				hole5.y = q11;

				// bottom middle circle
				hole6.x = q12;
				hole6.y = q13;

			

				// add all 8 random holes into an arraylist
				holes.add(hole);
				holes.add(hole1);
				holes.add(hole2);
				holes.add(hole3);
				holes.add(hole4);
				holes.add(hole5);
				holes.add(hole6);


				// random index to choose from array
				int index = (int) (Math.random() * 7 - 0) + 0;
				// Pick the random hole for the mole to come out of
				Hole theone = holes.get(index);
				mole.x = theone.x;
				mole.y = theone.y;
	
			}

		
			hole.animate();
			hole1.animate();
			hole2.animate();
			hole3.animate();
			hole4.animate();
			hole5.animate();
			hole6.animate();
	
			
			// add a delay here
	
			mole.animate();
		

		});
		animator.start();

	
	}

	public static void main(String[] args)  {
		Frame x = new Frame();

		
	}
}
