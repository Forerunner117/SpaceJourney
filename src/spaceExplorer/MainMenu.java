package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

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
    public void init(GameContainer arg0) throws SlickException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(GameContainer arg0, Graphics arg1) throws SlickException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(GameContainer arg0, int arg1) throws SlickException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public State nextState() {
        return nextState;
    }
    
    @Override
    public boolean closeRequested() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
   
}
