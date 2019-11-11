import java.awt.*;

import javax.swing.Timer;

/**
 * A shape that can change its size. Shapes that need to grow/shrink will implement this interface.
 *
 * @author Kenny Nguyen
 * @version 1.0
 * @since 2019-11-1
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
}