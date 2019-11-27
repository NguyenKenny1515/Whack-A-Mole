import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

/**
 * Displays the entire game inside JFrame.
 */
public class Frame extends JFrame {

    private static final int EASY = 800;
    private static final int NORMAL = 650;
    private static final int HARD = 500;

    private String scores;
    private String name;

    public Frame() {

        // Creates the Frame
        JFrame frame = new JFrame("Whack-A-Mole");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame size to computer's screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

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

        // Creates the scene
        final SceneComponent scene = new SceneComponent();

        // ArrayList to store holes.
        ArrayList<Hole> holes = new ArrayList<>();
        
        // A String to store all scores.
        scores = "";

        // When game starts up, asks for user's name to save their scores to hi-scores
        name = JOptionPane.showInputDialog("Enter Player Name");

        // If user presses cancel, exits the program
        if (name == null)
            System.exit(0);

        // Asks users what difficulty they want to play on
        String[] difficultyChoices = {"Easy", "Normal", "Hard"};
        int difficulty = JOptionPane.showOptionDialog(null, "Choose game difficulty: ",
                "Choose difficulty", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, difficultyChoices, difficultyChoices[1]);
        // Sets speed at which Holes and Mole spawn depending on difficulty selected
        int speed = 0;
        if (difficulty == 0)
            speed = EASY;
        else if (difficulty == 1)
            speed = NORMAL;
        else if (difficulty == 2)
            speed = HARD;
        else
            System.exit(0);

        // Plays the music after difficulty has been chosen and speed has been set
        Audio backgroundMusic = new Audio("src\\GameMusic.wav");
        backgroundMusic.play();

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

        // Sets up holes and mole on screen and adds animation timers to them so they can grow/shrink
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

        // Creates the high score screen
        JLabel allScores = new JLabel();
        allScores.setBounds(0,0,300,500);

        // Run the actual game play
        Timer animator = new Timer(speed, animationEvent -> {
            // If any Hole shrinks down to 0 (board is clear and has no Holes)
            if (hole.getWidth() == 0) {

                // If Timer is not already running, start it (for at the start)
                if (!scene.hasTimerStarted())
                    scene.startTimer();

                // Resets "earned a point" indicator
                scene.resetPointAnimation();

                // If the timer hits 0 (game ended)
                if (scene.getTime() == 0) {
                    backgroundMusic.stop();
                    scene.setTimerStarted(false);

                    scores = scores + name + "  -  " + scene.getScore() + "<br/>";

                    // Ask users for their next action: play again, view highest scores, or quit program
                    String[] options = {"Play again", "Hi-Scores", "Exit"};
                    int userChoice = -1;
                    while (userChoice != 0) {
	                    userChoice = JOptionPane.showOptionDialog(null, "GAME OVER! " +
	                                    "Your score was: " + scene.getScore(), "Click a button", JOptionPane.DEFAULT_OPTION,
	                            JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
	
	                    // If user chose play again, ask for their name and replays game on same difficulty
	                    if (userChoice == 0) {
	                        name = JOptionPane.showInputDialog("Enter Player Name");
	                        scene.setScore(0);
	                        scene.setTime(60);
	                        scene.startTimer();
	                        scene.setTimerStarted(true);
	                        try {
	                            backgroundMusic.restart();
	                        }
	                        catch(Exception e) {
	                            System.out.println("Error with playing sound.");
	                            e.printStackTrace();
	                        }
	                    }
	                    // If user chose to view scores, display a separate Frame showing list of highest scores
	                    // of this session.
	                    else if (userChoice == 1) {
	                        JFrame hiscores = new JFrame();
	                        allScores.setText("<html>" + scores + "</html>");
	                        hiscores.add(allScores);
	                        hiscores.setSize(300,600);
	                        hiscores.setLayout(new FlowLayout());
	                        hiscores.setVisible(true);
	                    }
                    // If user chose exit, quit the program
                    if (userChoice == 2) {
                        System.exit(0);
                    }
                }

                // Generate random coordinates for bottom left Hole
                hole.setX((int) (Math.random() * (screenSize.width / 5 - 125)) + 125);
                hole.setY((int) (Math.random() * ((screenSize.height - 100) - screenSize.height * 5/7)) +
                        screenSize.height * 5/7);

                // Generate random coordinates for bottom left middle Hole
                hole2.setX((int) (Math.random() * (screenSize.width /2 - screenSize.width /4)) +
                        screenSize.width /4);
                hole2.setY((int) (Math.random() * ((screenSize.height - 100 - screenSize.height * 3/4)) +
                        screenSize.height * 3/4));

                // Generate random coordinates for bottom right middle Hole
                hole3.setX((int) (Math.random() * (((screenSize.width * 3/4 - 50 )- screenSize.width /2 - 25) -
                        screenSize.width / 2 - 25)) +
                        screenSize.width * 4 / 5);
                hole3.setY((int) (Math.random() * ((screenSize.height - 100 - screenSize.height * 3/4 + 5)) +
                        screenSize.height * 3/4 + 5));

                // Generate random coordinates for bottom right Hole
                hole4.setX((int) (Math.random() *( (screenSize.width * 3/4) - screenSize.width * 3/4 + 125)) +
                        screenSize.width * 3/4 + 125);
                hole4.setY((int) (Math.random() * ((screenSize.height - 125 ) - screenSize.height * 2 / 3 + 5 + 25)) +
                        screenSize.height * 2 / 3 + 25);

                // Generate random coordinates for top left Hole
                hole5.setX((int) (Math.random() * ((screenSize.width / 2 - 25)  - screenSize.width /4)) +
                        screenSize.width / 4);
                hole5.setY((int) (Math.random() * ((screenSize.height * 3/4 - 100 ) - screenSize.height * 3/5 + 25)) +
                        screenSize.height  * 3 / 5 + 25);

                // Generate random coordinates for top right Hole
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
            mole.animate();
        }
        );
        animator.start();

        // Must appear in this order or else png wallpaper gets masked over holes & mole
        frame.add(scene);
        frame.setVisible(true);
        frame.add(background);
    }

    public static void main(String[] args) {
        Frame x = new Frame();
 
    }
}