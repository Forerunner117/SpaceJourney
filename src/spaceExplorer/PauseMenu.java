package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @author Austin
 *
 */
public class PauseMenu extends State {
    private static PauseMenu instance = null;
    private State nextState;
    private Image levelWallpaper;    
    private Image resumeImg;
    private Image mainMenuImg;
    private Image exitImg;

    private Button resumeButton;
    private Button mainMenuButton;
    private Button exitButton;
    
    /** @return Singleton instance of Pause Menu. */
    public static PauseMenu getInstance() {
        if (instance == null) {
            instance = new PauseMenu();
        }
        return instance;
    }

    /** Private constructor to initialize next state to this. */
    private PauseMenu() {
        nextState = this;
    }

    @Override
    public State nextState() {
        return nextState;
    }
    
    private void setNextState(State state){
        nextState = state;
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
        levelWallpaper = new Image(
                "resources/levels/space-wallpaper-level1.jpg");
        resumeImg = new Image("resources/buttons/resumeButton.png");
        mainMenuImg = new Image("resources/buttons/mainMenuButton.png");
        exitImg = new Image("resources/buttons/exitButton.png");
        
        resumeButton = new Button(280, 100, 340, 58, resumeImg);
        mainMenuButton = new Button(280, 300, 326, 59, mainMenuImg);
        exitButton = new Button(287, 500, 325, 59, exitImg);
    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel gm) {
        g.drawImage(levelWallpaper, 0, 0);
        g.drawImage(resumeImg, 280, 100);
        g.drawImage(mainMenuImg, 280, 300);
        g.drawImage(exitImg, 287, 500);
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        Input input = gc.getInput();
        
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {        
            int x = input.getMouseX();
            int y = input.getMouseY();
                          
            if (resumeButton.checkClick(x, y)){
                setNextState(LevelOne.getInstance());
            }
            
            if (mainMenuButton.checkClick(x, y)){
                setNextState(MainMenu.getInstance());
            }
            
            if(exitButton.checkClick(x, y)){
                gc.exit();
            }
        }
        
    }

}
