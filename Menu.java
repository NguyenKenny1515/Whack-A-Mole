import java.awt.Graphics;

/**
 * Prompts the user to start or quit the game. Displays when the user runs the program or when the player is finished
 * playing the game.
 */
public class Menu {

    public void render(Graphics g) {
        g.drawString("SPACE GAME", Game.WIDTH/ 2, 100);
    }

}