import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.management.timer.Timer;
import javax.swing.JComponent;

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
	
	public int score;

	public int time = 60;
	

	Timer timer = new Timer();
	/*TimerTask task = new TimerTask() {
		public void run() {
			time--;
			timer.scheduleAtFixedRate(task,1000,1000);
		}
	};*/
	
	


		   
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      g.setFont(new Font("Arial", Font.PLAIN, 30));
      g.drawString("SCORE: " + score, 1, 30);
      g.setFont(new Font("Arial", Font.PLAIN, 30));
      g.drawString("TIMER: " + time, 1750 , 30);
 
  
      
  
      for (GrowableShape s : shapes)
      {
         s.draw(g2);
      }
   }
}
