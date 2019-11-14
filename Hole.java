import java.awt.*;
import java.awt.geom.*;

import javax.swing.Timer;

enum Status {GROWING, SHRINKING}

/**
 * A black, static Hole that expands when a Mole randomly pops out of it. The Hole will then shrink back down to
 * its initial size when the Mole disappears.
 *
 * @author Kenny Nguyen
 * @version 1.0
 * @since 2019-11-1
 */
public class Hole implements GrowableShape {

    public int x;
    public int y;
    private int width;
    private int height;
    private boolean animating = true;
    private Status status;
    private static final int FULL_SIZE = 200;

    /**
     Constructs a Hole (ellipse).
     @param x the X coordinate of the center of the Ellipse
     @param y the Y coordinate of the center of the Ellipse
     @param width the width of the Hole
     @param height the height of the Hole
     */
    public Hole(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.status = Status.GROWING;
    }

    public void addAnimateTimer(Timer t)
	{
		t.addActionListener(animateEvent ->
		{
			if (animating && this.width < FULL_SIZE && status == Status.GROWING)
			{
				this.width += 2;
				this.height ++;
				if (this.width >= FULL_SIZE) { animating = false; status = Status.SHRINKING; }
			}
			else if (animating && this.width > 0 && status == Status.SHRINKING)
			{
				this.width -= 2;
				this.height --;
				if (this.width <= 0) { animating = false; status = Status.GROWING; }
			}
		}
		);
	}
	
	public void animate()
	{
		this.animating = true;
	}

    public void draw(Graphics2D g2) {
        Ellipse2D.Double hole = new Ellipse2D.Double(x - (width/2), y - (height/2), width, height);
        g2.setColor(Color.BLACK);
        g2.draw(hole);
        g2.fill(hole);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
    	return this.height;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String toString() {
    	return "X: " + this.x + " " + "Y: " + this.y;
    }

	public boolean contains(Point point) {
		// TODO Auto-generated method stub
		return true;
	}
}
