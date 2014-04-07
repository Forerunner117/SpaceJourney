package spaceExplorer;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class State {
    public abstract State nextState();
    public abstract void init(GameContainer gc) throws SlickException;
    public abstract void update(GameContainer gc, int delta, GameModel model);
    public abstract void render(GameContainer gc, Graphics g, GameModel model);
}
