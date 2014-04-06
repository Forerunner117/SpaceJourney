package spaceExplorer;

import org.newdawn.slick.Input;

public abstract class State {
    public abstract void render();
    public abstract State nextState();
    public abstract void update(GameModel model, Input input);
}
