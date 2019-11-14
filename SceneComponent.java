import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

/**
 * A component that shows a scene composed of shapes that is displayed by Frame.
 */
public class SceneComponent extends JComponent {

	public static final int TIME = 60;

	private ArrayList<GrowableShape> shapes;
	public int score;

	public SceneComponent() {
		shapes = new ArrayList<>();
	}

	/**
	 Adds a shape to the scene.
	 @param s the shape to add
	 */
	public void add(GrowableShape s) {
		shapes.add(s);
		repaint();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("SCORE: " + score, 1, 30);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("TIMER: " + TIME, 1750 , 30);
		for (GrowableShape s : shapes)
			s.draw(g2);
	}

	//Timer timer = new Timer();
	/*TimerTask task = new TimerTask() {
		public void run() {
			time--;
			timer.scheduleAtFixedRate(task,1000,1000);
		}
	};*/
}