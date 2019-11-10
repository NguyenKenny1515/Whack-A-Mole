import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.Icon;

public class Mole implements GrowableShape
{
	private int x;
	private int y;
	private int size;
	private int height;
	private static final Color MOLE_COLOR = new Color(0xD0A43D);
	private static final Color NOSE_COLOR = new Color(0xF299B1);

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
	}

	public void grow() {
		size++;
		height ++;
	}

	public void shrink() {
		size--;
		height --;
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

		g2.setColor(MOLE_COLOR);
		g2.fill(head);
		g2.fill(body);
		g2.setColor(NOSE_COLOR);
		g2.fill(nose);
		g2.setColor(Color.BLACK);
		g2.draw(leftNostril);
		g2.draw(rightNostril);
	}
}
