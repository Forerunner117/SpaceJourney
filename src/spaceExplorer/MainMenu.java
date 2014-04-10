package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** @author Chris */
public class MainMenu extends State {
    private static MainMenu instance = null;
    private State nextState;
    private Image levelWallpaper;
    private Image startButton;
    private Image highScoreButton;
    private Image creditsButton;
    private Image exitButton;

    /** @return Singleton instance of Main Menu. */
    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    /** Private constructor to initialize next state to this. */
    private MainMenu() {
        nextState = this;
        try {
            levelWallpaper = new Image(
                    "resources/levels/space-wallpaper-level1.jpg");
            startButton = new Image("resources/buttons/startButton.png");
            highScoreButton = new Image("resources/buttons/highScoreButton.png");
            creditsButton = new Image("resources/buttons/creditsButton.png");
            exitButton = new Image("resources/buttons/exitButton.png");
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public State nextState() {
        return nextState;
    }

    @Override
    public void init(GameContainer gc) {
    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel gm) {
        g.drawImage(levelWallpaper, 0, 0);
        g.drawImage(startButton, 280, 100);
        g.drawImage(highScoreButton, 274, 250);
        g.drawImage(creditsButton, 301, 400);
        g.drawImage(exitButton, 287, 550);
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        // TODO Auto-generated method stub

    }

}
