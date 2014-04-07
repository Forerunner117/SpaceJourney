package spaceExplorer;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SpaceExplorer extends BasicGame {
    private State state;
    private GameModel model;

    public SpaceExplorer() {
        super("Space Explorer");
        state = Level.getInstance();
        model = GameModel.getInstance();
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
    public void init(GameContainer gc) throws SlickException {
        state.init(gc);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        state.render(gc, g, model);
    }
    
    //TODO Update the game model too 
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        state.update(gc, delta, model);
    }

}
