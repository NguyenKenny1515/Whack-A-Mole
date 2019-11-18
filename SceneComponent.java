import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;

/**
 * A component that shows a scene composed of shapes that is displayed by Frame.
 */
public class SceneComponent extends JComponent{

	private int time;
	private int score;
	private Point mousePoint;
	private ArrayList<GrowableShape> shapes;

	public SceneComponent() {
		this.shapes = new ArrayList<>();
		this.time = 60;
		addMouseListener(new MousePressedListener());
	}
	
	private class MousePressedListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent event)
		{
			mousePoint = event.getPoint();
			for (GrowableShape s : shapes)
			{
				if (s.contains(mousePoint) && s.getClass() == Mole.class && ((Mole)s).isHittable())
				{
					((Mole)s).hit();
					score++;
					break;
				}
			}
			repaint();         
		}
	}

	/**
	 Adds a shape to the scene.
	 @param s the shape to add
	 */
	public void add(GrowableShape s) {
		shapes.add(s);
		repaint();
	}
	
	public int getTime() {
		return time;
	}

	public int getScore() {
		return score;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (GrowableShape s : shapes)
			s.draw(g2);

		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("SCORE: " + score, 1, 30);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("TIMER: " + time, 1750 , 30);
	}

	//Timer timer = new Timer();
	/*TimerTask task = new TimerTask() {
		public void run() {
			time--;
			timer.scheduleAtFixedRate(task,1000,1000);
		}
	};*/
}