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
    private Image planet;
    private Image levelWallpaper;
    private State nextState;
    private SpaceEnvironment space = parent.getSpaceEnvironment();
    private static double startTime;
    private double stopTime;
    private double totalTime;

    /** @return An instance of Level2 */
    public static LevelTwo getInstance() {
        if (instance == null) {
            instance = new LevelTwo();
        }
        
        startTime = System.currentTimeMillis();
        return instance;
    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel model) {
        g.drawImage(levelWallpaper, 0, 0);
        g.drawImage(parent.taco, model.getTacoPixelX(), model.getTacoPixelY());
        g.drawImage(parent.currentAstronaut, model.getPixelX(),
                model.getPixelY());
        g.drawImage(planet, 450, 500);
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

        if (model.hasTaco()) {
            //stop the clock and add the new score to the CSV file
            stopTime = System.currentTimeMillis();
            totalTime = stopTime-startTime;
            HighScoreUtil.addScore(Integer.toString(2), model.getPlayerName(), Integer.toString((int)totalTime));
            
            setNextState(Credits.getInstance());
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
            planet = new Image("resources/planets/cartoon-planet-4.png");
        } catch (SlickException e) {
            throw new RuntimeException();
        }
        space.registerTaco(700, 200);
        space.createPlanet(457, 500, 90);
    }

}
