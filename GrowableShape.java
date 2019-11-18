import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.Timer;

/**
 * A shape that can change its size. Shapes that need to grow/shrink will implement this interface.
 *
 */
public interface GrowableShape {

    /**
     Draws the shape.
     @param g2 the graphics context
     */
    void draw(Graphics2D g2);

    /**
     Increases or decreases the size of the shape.
     */
    void animate();

    /**
     * Gives the GrowableShape a Timer that will allow it to grow/shrink
     * @param t the animation Timer
     */
    void addAnimateTimer(Timer t);

    boolean contains(Point2D p);
}