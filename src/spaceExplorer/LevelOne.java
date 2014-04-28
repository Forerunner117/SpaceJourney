package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** Level 1 of the Game
 * 
 * @author Chris
 * @author Austin */
public class LevelOne extends Level {
    private static LevelOne instance = null;
    private Image levelWallpaper;
    private Image planetImage;
    private Level parent = Level.getInstance();
    private State nextState;
    private SpaceEnvironment space = parent.getSpaceEnvironment();
    private static double startTime;
    private double stopTime;
    private double totalTime;

    /** @return An instance of level 1. */
    public static LevelOne getInstance() {
        if (instance == null) {
            instance = new LevelOne();
        }

        startTime = System.currentTimeMillis();
        return instance;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        parent.init(gc);
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        nextState = this;
        parent.update(gc, delta, model);
        Input input = gc.getInput();

        space.moveSprite(model);

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            setNextState(PauseMenu.getInstance());
        }

        if (model.hasTaco()) {
            // stop the clock and add the new score to the CSV file
            stopTime = System.currentTimeMillis();
            totalTime = stopTime - startTime;
            HighScoreUtil.addScore(Integer.toString(1), model.getPlayerName(),
                    Integer.toString((int) totalTime));

            space.clearPlanets();
            model.resetSprite();
            setNextState(LevelTwo.getInstance());
        }

    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel model) {
        g.drawImage(levelWallpaper, 0, 0);
        g.drawImage(planetImage, 600, 20);
        g.drawImage(parent.taco, model.getTacoPixelX(), model.getTacoPixelY());
        g.drawImage(parent.currentAstronaut, model.getPixelX(),
                model.getPixelY());
    }

    /** Sets the next state.
     * 
     * @param state the next state to set. */
    private void setNextState(State state) {
        nextState = state;
    }

    @Override
    public State nextState() {
        return nextState;
    }

    /** Load the images and create the level. */
    private LevelOne() {
        try {
            levelWallpaper = new Image(
                    "resources/levels/space-wallpaper-level1.jpg");
            planetImage = new Image("resources/planets/cartoon-planet.png");
        } catch (SlickException e) {
            throw new RuntimeException();
        }
        space.createPlanet(630, 120, 5);
        space.registerTaco(700, 600);
        nextState = this;
    }

}
