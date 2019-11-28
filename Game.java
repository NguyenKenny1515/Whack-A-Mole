import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

enum STATE {MENU, GAME}

/**
 * Oversees the entire Game and runs the game mechanics. Keeps track of points and plays sounds/music.
 */
public class Game extends JFrame implements MouseListener {

    private STATE state;
    private Menu menu;

    public Game() {
        state = STATE.MENU;
        addMouseListener(this);
        menu = new Menu();
        if (state == STATE.MENU) {
            //menu.render(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }


}

