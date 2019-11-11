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

		final Mole mole = new Mole(250, 300, 0, 0);
		scene.add(mole);

		frame.add(scene);
		frame.getContentPane().setBackground(Color.GREEN);

		frame.setVisible(true);

		final int DELAY = 5;
		// Milliseconds between timer ticks
		Timer t = new Timer(DELAY, event ->
		{
			scene.repaint();
		});
		hole.addAnimateTimer(t);
		mole.addAnimateTimer(t);
		t.start();
		
		// Note: this is just a demo to show that every time you call GrowableShape.animate(), it performs one full grow/shrink animation
		Timer animator = new Timer(3000, animationEvent ->
		{
			hole.animate();
			mole.animate();
		}
		);
		animator.start();
	}

	public static void main(String[] args) {
		Frame x = new Frame();
	}
}