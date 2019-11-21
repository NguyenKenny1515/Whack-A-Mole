import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;

/**
 * A component that shows a scene composed of shapes that is displayed by Frame. Manages the entire game.
 */
public class SceneComponent extends JComponent{

	private int time;
	private int score;
	private Point mousePoint;
	private ArrayList<GrowableShape> shapes;
	private Dimension screenSize;
	private Audio hitSound;
	private boolean timerStarted;

	public SceneComponent() {
		this.shapes = new ArrayList<>();
		this.time = 60;
		addMouseListener(new MousePressedListener());
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		hitSound = new Audio("src\\HitSound.wav");
		timerStarted = false;
	}
	
	private class MousePressedListener extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			mousePoint = event.getPoint();
			for (GrowableShape s : shapes) {
				if (s.contains(mousePoint) && s.getClass() == Mole.class && ((Mole)s).isHittable()) {
					((Mole)s).hit();
					score++;
					hitSound.play();
				}
			}
			repaint();         
		}
	}

	public void startTimer() {
		Thread thread = new Thread(new Runnable () {
			@Override
			public void run() {
				for (int i = 60; i >= 1; i--) {
					try {
						Thread.sleep(1000);
						time--;
					}
					catch (InterruptedException e) {}
				}
			}
		});
		thread.start();
		timerStarted = true;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isTimerStarted() {
		return timerStarted;
	}

	public void setTimerStarted(boolean timerStarted) {
		this.timerStarted = timerStarted;
	}

	/**
	 Adds a shape to the scene.
	 @param s the shape to add
	 */
	public void add(GrowableShape s) {
		shapes.add(s);
		repaint();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.BLACK);
		for (GrowableShape s : shapes)
			s.draw(g2);

		g.setFont(new Font("Arial", Font.BOLD, 70));
		g.drawString("SCORE: " + score, 0, 65);
		g.setFont(new Font("Arial", Font.BOLD, 70));

		// Warning indicator
		if (time <= 10)
			g.setColor(Color.RED);

		g.drawString("TIMER: " + time, screenSize.width - 370 , 65);
	}
}