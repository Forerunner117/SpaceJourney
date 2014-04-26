package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** @author Chris */
public class LevelTwo extends Level {
    private static LevelTwo instance = null;
    private Level parent = Level.getInstance();
    private Image levelWallpaper;
    private State nextState;
    private SpaceEnvironment space = parent.getSpaceEnvironment();

    /** @return An instance of Level2 */
    public static LevelTwo getInstance() {
        if (instance == null) {
            instance = new LevelTwo();
        }
        return instance;
    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel model) {
        g.drawImage(levelWallpaper, 0, 0);
        g.drawImage(parent.taco, model.getTacoPixelX(), model.getTacoPixelY());
        g.drawImage(parent.currentAstronaut, model.getPixelX(), model.getPixelY());
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        nextState = this;
        parent.update(gc, delta, model);
        Input input = gc.getInput();

        space.orbitTaco(model);
        space.moveSprite(model);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            setNextState(PauseMenu.getInstance());
        }
    }

    private void setNextState(State state) {
        nextState = state;
    }

    @Override
    public State nextState() {
        return nextState;
    }

    /** Construct Privately */
    private LevelTwo() {
        nextState = this;
        try {
            levelWallpaper = new Image(
                    "resources/levels/space-wallpaper-level2.jpg");
        } catch (SlickException e) {
            throw new RuntimeException();
        }
        space.registerTaco(700, 200);
    }

}
