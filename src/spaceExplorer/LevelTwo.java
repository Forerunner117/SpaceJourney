package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** @author Chris */
public class LevelTwo extends Level {
    private static LevelTwo instance = null;
    private Level parent = Level.getInstance();
    private Image levelWallpaper;
    private State nextState;
    
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
        g.drawImage(parent.currentAstronaut, model.getX(), model.getY());
    }
    
    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        //TODO set as getting the taco or whatever.
        if(model.getX() > 200) {
            setNextState(this);
        }
        parent.update(gc, delta, model);
    }
    
    private void setNextState(State state){
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
            levelWallpaper = new Image("resources/levels/space-wallpaper-level2.jpg");
        } catch (SlickException e) {
            throw new RuntimeException();
        }
    }

}
