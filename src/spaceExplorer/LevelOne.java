package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author Chris
 *
 */
public class LevelOne extends Level {
    private static LevelOne instance = null;
    private Image levelWallpaper;
    private Level parent = Level.getInstance();
    private State nextState;

    public static LevelOne getInstance() {
        if (instance == null) {
            instance = new LevelOne();
        }
        return instance;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        levelWallpaper = new Image(
                "resources/levels/space-wallpaper-level1.jpg");
        parent.init(gc);
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        // TODO set as getting the taco or whatever.
        if (model.getX() > 100) {
            setNextState(LevelTwo.getInstance());
        }
        parent.update(gc, delta, model);
    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel model) {
        g.drawImage(levelWallpaper, 0, 0);
        g.drawImage(parent.currentAstronaut, model.getX(), model.getY());
    }

    private void setNextState(State state) {
        nextState = state;
    }

    @Override
    public State nextState() {
        return nextState;
    }

    private LevelOne() {
        nextState = this;
    }

}
