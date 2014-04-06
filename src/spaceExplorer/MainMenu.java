package spaceExplorer;

import org.newdawn.slick.Input;

public class MainMenu extends State {
    private static MainMenu instance = null;
    private State nextState;
    
    public static MainMenu getInstance() {
        if(instance == null){
            instance = new MainMenu();
        }
        return instance;
    }
    
    private MainMenu() {
        nextState = this;
    }
    
    @Override
    public void update(GameModel model, Input nput) {
        // TODO Auto-generated method stub
        
    }

    
    @Override
    public void render() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public State nextState() {
        return nextState;
    }

   
}
