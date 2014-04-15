package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

// Should update automatically

/** The Level Class. Where the game fun happens. Will possibly be subclassed by
 * other levels in the future.
 * 
 * @author Chris */
public class Level extends State {
    private static Level instance = null;
    private State nextState;
    protected Image[] astronautMan = new Image[12];
    protected Image currentAstronaut;
    private Image levelWallpaper;
    /** The height of the sprite. */
    public static final int SPRITEHEIGHT = 32;
    /** The Width of the sprite. */
    public static final int SPRITEWIDTH = 23;

    /** @return Singleton level instance. */
    public static Level getInstance() {
        if (instance == null) {
            instance = new Level();
        }
        return instance;
    }

    /** Private constructor to initialize next state to this. */
    protected Level() {
        nextState = this;
    }

    @Override
    public State nextState() {
        return nextState;
    }

    /** Sets the next state.
     * 
     * @param state The state to set. */
    private void setNextState(State state) {
        nextState = state;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        levelWallpaper = new Image(
                "resources/levels/space-wallpaper-level1.jpg");
        astronautMan[0] = new Image("resources/astronautMan/frontStand.png");
        astronautMan[1] = new Image("resources/astronautMan/forLeft.png");
        astronautMan[2] = new Image("resources/astronautMan/forRight.png");
        astronautMan[3] = new Image("resources/astronautMan/rightStand.png");
        astronautMan[4] = new Image("resources/astronautMan/rightRight.png");
        astronautMan[5] = new Image("resources/astronautMan/rightLeft.png");
        astronautMan[6] = new Image("resources/astronautMan/leftStand.png");
        astronautMan[7] = new Image("resources/astronautMan/leftRight.png");
        astronautMan[8] = new Image("resources/astronautMan/leftStand.png");
        astronautMan[9] = new Image("resources/astronautMan/backStand.png");
        astronautMan[10] = new Image("resources/astronautMan/backRight.png");
        astronautMan[11] = new Image("resources/astronautMan/backStand.png");
        currentAstronaut = astronautMan[0];
    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel model) {
        g.drawImage(levelWallpaper, 0, 0);
        g.drawImage(currentAstronaut, model.getX(), model.getY());
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_UP)) {
            currentAstronaut = astronautMan[9];
            model.setCoords(model.getX(), model.getY() - 1);
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            currentAstronaut = astronautMan[6];
            model.setCoords(model.getX() - 1, model.getY());
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            currentAstronaut = astronautMan[3];
            model.setCoords(model.getX() + 1, model.getY());
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            currentAstronaut = astronautMan[0];
            model.setCoords(model.getX(), model.getY() + 1);
        }
        if(model.getX() >= 100) {
            setNextState(Level2.getInstance());
        }
        // TODO find sensible platform independent way to delay the sprite
        // movement
        model.sleep(7);
    }

}
