package spaceExplorer;

import org.newdawn.slick.Input;

public class Level extends State {
    private static Level instance = null;
    private State nextState;
    
    public static Level getInstance() {
        if(instance == null){
            instance = new Level();
        }
        return instance;
    }
    
    private Level() {
        nextState = this;
    }
    
    @Override
    public void render() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public State nextState() {
        return nextState;
    }

    @Override
    public void update(GameModel model, Input nput) {
        // TODO Auto-generated method stub
        
    }

}
