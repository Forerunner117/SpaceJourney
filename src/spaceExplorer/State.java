package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/** State interface for implementing state pattern.
 * 
 * @author Chris */
public abstract class State {    
    /** @return the next state. */
    public abstract State nextState();

    /** Sets up the state. Load images and any resources.
     * 
     * @param gc - Game Container. Grab/set important game settings.
     * @throws SlickException */
    public abstract void init(GameContainer gc) throws SlickException;

    /** Update the state and game model.
     * 
     * @param gc Game Container. Grab/set important game settings.
     * @param delta - Time delay
     * @param model - model of the game. */
    public abstract void update(GameContainer gc, int delta, GameModel model);

    /** Draws everything. Minimal logic.
     * 
     * @param gc Game Container. Grab/set important game settings.
     * @param g Graphics object for drawing.
     * @param model model of the game. */
    public abstract void render(GameContainer gc, Graphics g, GameModel model);      
}
