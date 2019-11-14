import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Displays the entire game inside JFrame.
 */
public class Frame extends JFrame {

    public Frame() {

        JFrame frame = new JFrame("Whack-A-Mole");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame size to computer's screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

        final SceneComponent scene = new SceneComponent();

        // Prototype menu screen
        JOptionPane.showMessageDialog(null, "Press Ok To Start","Start",
                JOptionPane.INFORMATION_MESSAGE);

        // Creates 5 Holes and Mole and adds them to the scene
        final Hole hole = new Hole(-100, 0, 0, 0);
        final Hole hole2 = new Hole(-100, 0, 0, 0);
        final Hole hole3 = new Hole(-100, 0, 0, 0);
        final Hole hole4 = new Hole(-100, 0, 0, 0);
        final Hole hole5 = new Hole(-100, 0, 0, 0);
        final Hole hole6 = new Hole(-100, 0, 0, 0);
        final Mole mole = new Mole(-100, 0, 0, 0);
        scene.add(hole);
        scene.add(hole2);
        scene.add(hole3);
        scene.add(hole4);
        scene.add(hole5);
        scene.add(hole6);
        scene.add(mole);

        // Sets up holes and mole on screen
        final int DELAY = 0;
        Timer t = new Timer(DELAY, event -> {
            scene.repaint();
        });
        hole.addAnimateTimer(t);
        hole2.addAnimateTimer(t);
        hole3.addAnimateTimer(t);
        hole4.addAnimateTimer(t);
        hole5.addAnimateTimer(t);
        hole6.addAnimateTimer(t);
        mole.addAnimateTimer(t);
        t.start();

        // Generate random hole and mole locations and have them start appearing
        ArrayList<Hole> holes = new ArrayList<>();
        Timer animator = new Timer(1000, animationEvent ->
        {
            // If any Hole shrinks down to 0 (board is clear and has no Holes), find a new random x and y to respawn
            if (hole.getWidth() == 0) {
                //bottom left
                hole.setX((int) (Math.random() * (screenSize.width / 5 - 125)) + 125);
                hole.setY((int) (Math.random() * ((screenSize.height - 100) - screenSize.height * 5/7)) +
                        screenSize.height * 5/7);

                // bottom left middle
                hole2.setX((int) (Math.random() * (screenSize.width /2 - screenSize.width /4)) +
                        screenSize.width /4);
                hole2.setY((int) (Math.random() * ((screenSize.height - 100 - screenSize.height * 3/4)) +
                        screenSize.height * 3/4));

                // bottom right middle
                hole3.setX((int) (Math.random() * (((screenSize.width * 3/4 - 50 )- screenSize.width /2 - 25) -
                        screenSize.width / 2 - 25)) +
                        screenSize.width * 4 / 5);
                hole3.setY((int) (Math.random() * ((screenSize.height - 100 - screenSize.height * 3/4 + 5)) +
                        screenSize.height * 3/4 + 5));

                // bottom right
                hole4.setX((int) (Math.random() *( (screenSize.width * 3/4) - screenSize.width * 3/4 + 125)) +
                        screenSize.width * 3/4 + 125);
                hole4.setY((int) (Math.random() * ((screenSize.height - 125 ) - screenSize.height * 2 / 3 + 5 + 25)) +
                        screenSize.height * 2 / 3 + 25);

                // top left
                hole5.setX((int) (Math.random() * ((screenSize.width / 2 - 25)  - screenSize.width /4)) +
                        screenSize.width / 4);
                hole5.setY((int) (Math.random() * ((screenSize.height * 3/4 - 100 ) - screenSize.height * 3/5 + 25)) +
                        screenSize.height  * 3 / 5 + 25);

                // top right
                hole6.setX((int) (Math.random() * ((screenSize.width * 6 /7 - 250) - screenSize.width * 4/7) - 25) +
                        screenSize.width * 4/7 - 25);
                hole6.setY((int) (Math.random() * ((screenSize.height * 4/5 - 75 ) - screenSize.height * 3/5)) +
                        screenSize.height  * 3/5);

                holes.add(hole);
                holes.add(hole2);
                holes.add(hole3);
                holes.add(hole4);
                holes.add(hole5);
                holes.add(hole6);

                // Pick the random hole for the mole to come out of
                int random = (int) (Math.random() * 6 - 0) + 0;
                Hole randomHole = holes.get(random);
                mole.setX(randomHole.getX());
                mole.setY(randomHole.getY());
            }

            hole.animate();
            hole2.animate();
            hole3.animate();
            hole4.animate();
            hole5.animate();
            hole6.animate();

            // Add delay here so mole doesn't come out too fast
            mole.animate();
        }
        );
        animator.start();

        // Changes default Windows cursor to custom hammer image
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("src\\hammer.png");
        Image scaledImage = image.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        Cursor cursor = toolkit.createCustomCursor(scaledImage , new Point(frame.getX(), frame.getY()), "hammer");
        frame.setCursor(cursor);

        // Sets background.png as wallpaper
        JLabel background = new JLabel("");
        background.setIcon(new ImageIcon("src\\background.png"));
        background.setBounds(0,0, screenSize.width, screenSize.height);

        // Must appear in this order or else png gets masked over holes & mole
        frame.add(scene);
        frame.setVisible(true);
        frame.add(background);
    }

    public static void main(String[] args) {
        Frame x = new Frame();
        Audio a = new Audio("src\\GameMusic.wav");
        a.play();
    }
}