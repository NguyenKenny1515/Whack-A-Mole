import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Displays the entire game inside JFrame.
 */
public class Frame extends JFrame {

	public Frame() {
		
		JFrame frame = new JFrame("Hello");
		frame.setSize(950,950);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final SceneComponent scene = new SceneComponent();
		
		final Hole hole = new Hole(250, 300, 0, 0);
		scene.add(hole);
		//ShapeIcon holeIcon = new ShapeIcon(hole, 50, 50);
		//JLabel holeLabel = new JLabel(holeIcon);

		final Mole mole = new Mole(250, 300, 0, 0);
		scene.add(mole);
		//ShapeIcon moleIcon = new ShapeIcon(mole, 10, 10);
		//JLabel moleLabel = new JLabel(moleIcon);

		//frame.add(holeLabel);
		//frame.add(moleLabel);
		frame.add(scene);

		frame.getContentPane().setBackground(Color.GREEN);

		frame.setVisible(true);

		final int DELAY = 5;
		// Milliseconds between timer ticks
		Timer t = new Timer(DELAY, event ->
		{
			if (hole.getStatus() == Status.GROWING) {
				hole.grow();
				mole.grow();
			}
			else {
				hole.shrink();
				mole.shrink();
			}

			//holeLabel.repaint();
			//moleLabel.repaint();
			scene.repaint();

		});
		t.start();
		while (true) {
			if (hole.getStatus() == Status.GROWING && hole.getWidth() > 250) {
				hole.setStatus(Status.SHRINKING);
			}
			else if (hole.getStatus() == Status.SHRINKING && hole.getWidth() <= 1) {
				t.stop();
				break;
			}
			System.out.println("Running"); // DO NOT REMOVE OR ELSE ANIMATION WILL BREAK
		}
		System.out.println("No longer running");
	}

	public static void main(String[] args) {
		Frame x = new Frame();
	}
}