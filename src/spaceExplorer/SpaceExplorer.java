package spaceExplorer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/** @author Chris */
public class SpaceExplorer extends BasicGame {
    
    /** The width of the window. */
    public static final int WIDTH = 900;
    /** The height of the window. */
    public static final int HEIGHT = 700;

    private State state;
    private GameModel model;

    /** Get instance of all states */
    public SpaceExplorer() {
        super("Space Explorer");
        state = MainMenu.getInstance();
        model = GameModel.getInstance();
    }

    /** Start Game here
     * 
     * @param args */
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new SpaceExplorer());
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /** Gets called once before game loop
     * 
     * @param gc GameContainer that has various methods.
     * @throws SlickException */
    @Override
    public void init(GameContainer gc) throws SlickException {
        state.init(gc);
    }

    /** Gets called continuously in game loop. Delegates everything.
     * 
     * @param gc GameContainer for various useful methods
     * @param g Graphics object for drawing.
     * @throws SlickException */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        state.render(gc, g, model);
        state = state.nextState();
    }

    /** Gets called continuously in game loop. Updates the state which updates
     * the model.
     * 
     * @param gc Game Container.
     * @param delta - Time delay.
     * @throws SlickException */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        state.update(gc, delta, model);
    }

}
