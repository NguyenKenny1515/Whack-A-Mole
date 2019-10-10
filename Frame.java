import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Frame extends JFrame {
	Graphics g;

	public Frame() {
		JFrame frame = new JFrame("Hello");
	    frame.setSize(950,950);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		frame.paint(g);
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setVisible(true);
	}
	
	    
	public void paintComponent(Graphics g)
	 {
	 super.paintComponents(g);
	   g.setColor(Color.RED);
       g.drawOval(100, 100, 50, 50);
	 }

	
	public static void main(String[] args) {
		Frame x = new Frame();
		System.out.println(x);

	
	}
}
