import java.awt.*;
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
	private Dimension screenSize;
	private Audio hitSound;

	public SceneComponent() {
		this.shapes = new ArrayList<>();
		this.time = 60;
		addMouseListener(new MousePressedListener());
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		hitSound = new Audio("src\\HitSound.wav");
	}
	
	private class MousePressedListener extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			mousePoint = event.getPoint();
			for (GrowableShape s : shapes) {
				if (s.contains(mousePoint) && s.getClass() == Mole.class && ((Mole)s).isHittable()) {
					((Mole)s).hit();
					hitSound.play();
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

		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("SCORE: " + score, 0, 25);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("TIMER: " + time, screenSize.width - 175 , 25);
	}
  
  // SUPPOSED TO BE THE ONE-MINUTE COUNTDOWN (KENNY START HERE)
	//Timer timer = new Timer();
	/*TimerTask task = new TimerTask() {
		public void run() {
			time--;
			timer.scheduleAtFixedRate(task,1000,1000);
		}
	};*/
}