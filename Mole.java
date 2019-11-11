import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;

/**
 * A Mole that will randomly pop out of one of the five randomly placed Holes. Will shrink after a certain time or
 * disappear if it has been clicked on.
 */
public class Mole implements GrowableShape {

	private static final int FULL_SIZE = 150;

	private int x;
	private int y;
	private int size;
	private int height;
	private boolean animating;
	private Status status;

	/**
	 * Constructs a Mole
	 * @param xCoord the x coordinate of center of the base of the mole; i.e. the center of the hole he's coming out of
	 * @param yCoord the y coordinate of center of the base of the mole; i.e. the center of the hole he's coming out of
	 * @param width the diameter of the semicircle that is the mole's head, as well as the width of his body
	 * @param startHeight how tall the rectangular body of the mole is, so it should start at zero to show the mole emerging from the hole
	 */
	public Mole(int xCoord, int yCoord, int width, int startHeight) {
		this.x = xCoord;
		this.y = yCoord;
		this.size = width;
		this.height = startHeight;
		this.animating = true;
		this.status = Status.GROWING;
	}

	/**
	 * Adds an animation Timer to Mole that determines when the Hole should grow/shrink and when it should stop
	 * @param t the animation Timer
	 */
	public void addAnimateTimer(Timer t) {
		t.addActionListener(animateEvent ->
		{
			if (animating && this.size < FULL_SIZE && status == Status.GROWING) {
				this.size++;
				this.height++;
				if (this.size == FULL_SIZE) { animating = false; status = Status.SHRINKING; }
			}
			else if (animating && this.size > 0 && status == Status.SHRINKING) {
				this.size--;
				this.height--;
				if (this.size == 0) { animating = false; status = Status.GROWING; }
			}
		}
		);
	}

	/**
	 * Sets the animating  to true if the Mole is currently in animation (growing/shrinking)
	 */
	public void animate() {
		this.animating = true;
	}

	public void draw(Graphics2D g2) {
		Arc2D.Double head = new Arc2D.Double(this.x-(size/2), this.y-height-(size/2), size, size, 0,
				180, Arc2D.PIE);
		Rectangle2D.Double body = new Rectangle2D.Double(this.x-(size/2), this.y-height, size, height);
		Ellipse2D.Double nose = new Ellipse2D.Double(x - (size/4), y - height - size/4, (size/2), (size/4));
		Ellipse2D.Double leftNostril = new Ellipse2D.Double(x - (size/12) - (size/8), y - height - size/6,
				size/8, size/8);
		Ellipse2D.Double rightNostril = new Ellipse2D.Double(x + (size/12), y - height - size/6, size/8,
				size/8);

		g2.setColor(new Color(0xD0A43D));
		g2.fill(head);
		g2.fill(body);
		g2.setColor(new Color(0xF299B1));
		g2.fill(nose);
		g2.setColor(Color.BLACK);
		g2.draw(leftNostril);
		g2.draw(rightNostril);
	}
}

