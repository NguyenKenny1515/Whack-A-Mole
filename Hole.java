import java.awt.*;
import java.awt.geom.*;
import javax.swing.Timer;

enum Status {GROWING, SHRINKING}

/**
 * A black, static Hole that expands when a Mole randomly pops out of it. The Hole will then shrink back down to
 * its initial size when the Mole disappears.
 */
public class Hole implements GrowableShape {

    private static final int FULL_SIZE = 200;

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean animating;
    private Status status;

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
        this.animating = true;
        this.status = Status.GROWING;
    }

    /**
     * Adds an animation Timer to Hole that determines when the Hole should grow/shrink and when it should stop
     * @param t the animation Timer
     */
    public void addAnimateTimer(Timer t) {
        t.addActionListener(animateEvent -> {
            if (animating && this.width < FULL_SIZE && status == Status.GROWING) {
                this.width += 2;
                this.height++;
                if (this.width >= FULL_SIZE) { animating = false; status = Status.SHRINKING; }
            }
            else if (animating && this.width > 0 && status == Status.SHRINKING) {
                this.width -= 2;
                this.height--;
                if (this.width <= 0) { animating = false; status = Status.GROWING; }
            }
        });
    }

    /**
     * Sets the animating  to true if the Hole is currently in animation (growing/shrinking)
     */
    public void animate() {
        this.animating = true;
    }

    public void draw(Graphics2D g2) {
        Ellipse2D.Double hole = new Ellipse2D.Double(x - (width/2), y - (height/2), width, height);
        g2.setColor(Color.BLACK);
        g2.draw(hole);
        g2.fill(hole);
    }

    public String toString() {
        return "X: " + this.x + " " + "Y: " + this.y;
    }

    public boolean contains(Point point) {
        return true;
    }

    public int getWidth() {
        return width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean contains(Point2D p)
    {
    	return false;	// we never want to give points for clicking on a hole, and this is the lazy way of doing that
    }
    }