import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

/**
 * Displays the entire game inside JFrame.
 */
public class Frame extends JFrame {

	public Frame() {
		JFrame frame = new JFrame("Hello");
		frame.setSize(950,950);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final Hole hole = new Hole(50, 50, 0, 0);
		ShapeIcon icon = new ShapeIcon(hole, 50, 50);
		JLabel aLabel = new JLabel(icon);

		frame.add(aLabel);
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setVisible(true);

		final int DELAY = 0;
		// Milliseconds between timer ticks
		Timer t = new Timer(DELAY, event ->
		{
			hole.grow();
			aLabel.repaint();
		});
		t.start();
	}

	public static void main(String[] args) {
		Frame x = new Frame();
		System.out.println(x);
	}
}
