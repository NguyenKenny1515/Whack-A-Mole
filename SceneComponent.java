import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
   Used from Textbook Code
*/
public class SceneComponent extends JComponent
{
	private ArrayList<GrowableShape> shapes;
	
	public SceneComponent()
	{
		shapes = new ArrayList<>();
	}
   
	public void add(GrowableShape s)
	{
		shapes.add(s);
		repaint();
	}

   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      for (GrowableShape s : shapes)
      {
         s.draw(g2);
      }
   }
}