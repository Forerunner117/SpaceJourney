package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** @author Chris */
public class Level2 extends Level {
    private static Level2 instance = null;
    private Image levelWallpaper;
    private Level parent = Level.getInstance();
    private State nextState;
    
    /** @return An instance of Level2 */
    public static Level2 getInstance() {
        if (instance == null) {
            instance = new Level2();
        }
        return instance;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        levelWallpaper = new Image("resources/levels/space-wallpaper-level2.jpg");
    }
    
    @Override
    public void render(GameContainer gc, Graphics g, GameModel model) {
        g.drawImage(levelWallpaper, 0, 0);
        g.drawImage(parent.currentAstronaut, model.getX(), model.getY());
    }
    
    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        parent.update(gc, delta, model);
        if(model.getX() > 3 * SpaceExplorer.WIDTH / 4) {
            setNextState(Level.getInstance());
        }
    }
    
    private void setNextState(State state){
        nextState = state;
    }
    
    /** Do not construct */
    private Level2() {
        nextState = this;
        try {
            levelWallpaper = new Image("resources/levels/space-wallpaper-level2.jpg");
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
