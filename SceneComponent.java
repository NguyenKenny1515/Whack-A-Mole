import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * A component that shows a scene composed of shapes that is displayed by Frame.
 */
public class SceneComponent extends JComponent {
	private ArrayList<GrowableShape> shapes;
	
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
      for (GrowableShape s : shapes)
         s.draw(g2);
   }
}