package spaceExplorer;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public abstract class State implements Game {
    public abstract State nextState();    
}
