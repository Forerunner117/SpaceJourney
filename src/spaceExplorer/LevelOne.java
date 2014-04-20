package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** @author Chris */
public class LevelOne extends Level {
    private static LevelOne instance = null;
    private Image levelWallpaper;
    private Level parent = Level.getInstance();
    private State nextState;
    private PlanetaryAcceleration pa = new PlanetaryAcceleration(SpaceExplorer.HEIGHT, SpaceExplorer.WIDTH, 3.2);
    
    public static LevelOne getInstance() {
        if (instance == null) {
            instance = new LevelOne();
        }
        return instance;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        parent.init(gc);
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        parent.update(gc, delta, model);
        Input input = gc.getInput();
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {        
            int x = input.getMouseX();
            int y = input.getMouseY();
            model.setCoords(x, y);
        }
        System.out.println(pa.nextPoint(model));
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
        try {
            levelWallpaper = new Image(
                    "resources/levels/space-wallpaper-level1.jpg");
        } catch (SlickException e) {
            throw new RuntimeException();
        }
        nextState = this;
    }

}
