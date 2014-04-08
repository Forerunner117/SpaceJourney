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
    }

    @Override
    public State nextState() {
        return nextState;
    }

    @Override
    public void init(GameContainer gc) {
        try {
            levelWallpaper = new Image(
                    "resources/levels/space-wallpaper-level1.jpg");
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel gm) {
        g.drawImage(levelWallpaper, 0, 0);
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        // TODO Auto-generated method stub

    }

}
