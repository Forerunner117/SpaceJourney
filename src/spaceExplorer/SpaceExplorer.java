package spaceExplorer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SpaceExplorer implements Game {
    private State state;
    private GameModel gm;

    public SpaceExplorer() {
        // Will be main menu in the final product
        state = Level.getInstance();
        gm = GameModel.getInstance();
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new SpaceExplorer());
            app.setDisplayMode(500, 400, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer arg0) throws SlickException {
        state.init(arg0);
    }

    @Override
    public void render(GameContainer arg0, Graphics arg1) throws SlickException {
        state.render(arg0, arg1);
    }
    
    //TODO Update the game model too model
    @Override
    public void update(GameContainer arg0, int arg1) throws SlickException {
        state.update(arg0, arg1);
    }

    @Override
    public boolean closeRequested() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getTitle() {
        return state.getTitle();
    }

}
