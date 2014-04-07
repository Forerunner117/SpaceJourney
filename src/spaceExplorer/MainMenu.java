package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/** @author Chris */
public class MainMenu extends State {
    private static MainMenu instance = null;
    private State nextState;

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
        // TODO Auto-generated method stub

    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel gm) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        // TODO Auto-generated method stub

    }

}
