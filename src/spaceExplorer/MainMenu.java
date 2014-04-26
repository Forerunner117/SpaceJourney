package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** @author Austin */
public class MainMenu extends State {
    private static MainMenu instance = null;
    private State nextState;
    private Image startImg;
    private Image highScoreImg;
    private Image creditsImg;
    private Image exitImg;

    private Button startButton;
    private Button highScoreButton;
    private Button creditsButton;
    private Button exitButton;
    
    
    
    public static Image menuWallpaper;
    
    /** @return Singleton instance of Main Menu. */
    public static MainMenu getInstance() {
        if (instance == null) {
            try {
                instance = new MainMenu();
            } catch (SlickException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return instance;
    }

    /** Private constructor to initialize next state to this. 
     * @throws SlickException */
    private MainMenu() throws SlickException {
        nextState = this;
        
        /*levelWallpaper = new Image(
                "resources/levels/space-wallpaper-menu.jpg");
        startImg = new Image("resources/buttons/startButton.png");
        highScoreImg = new Image("resources/buttons/highScoreButton.png");
        creditsImg = new Image("resources/buttons/creditsButton.png");
        exitImg = new Image("resources/buttons/exitButton.png");
        
        startButton = new Button(280, 100, 340, 58, startImg);
        highScoreButton = new Button(274, 250, 352, 59, highScoreImg);        
        creditsButton = new Button(301, 400, 297, 59, creditsImg);
        exitButton = new Button(287, 550, 325, 59, exitImg);*/
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
        menuWallpaper = new Image(
                "resources/levels/space-wallpaper-menu.jpg");
        startImg = new Image("resources/buttons/startButton.png");
        highScoreImg = new Image("resources/buttons/highScoreButton.png");
        creditsImg = new Image("resources/buttons/creditsButton.png");
        exitImg = new Image("resources/buttons/exitButton.png");
        
        startButton = new Button(280, 100, 340, 58, startImg);
        highScoreButton = new Button(274, 250, 352, 59, highScoreImg);        
        creditsButton = new Button(301, 400, 297, 59, creditsImg);
        exitButton = new Button(287, 550, 325, 59, exitImg);
    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel gm) {
        g.drawImage(menuWallpaper, 0, 0);
        g.drawImage(startButton.getButtonImage(), startButton.getX(), startButton.getY());
        g.drawImage(highScoreButton.getButtonImage(), highScoreButton.getX(), highScoreButton.getY());
        g.drawImage(creditsButton.getButtonImage(), creditsButton.getX(), creditsButton.getY());
        g.drawImage(exitButton.getButtonImage(), exitButton.getX(), exitButton.getY());
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        nextState = this;
        Input input = gc.getInput();
        
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {        
            int x = input.getMouseX();
            int y = input.getMouseY();
                          
            if (startButton.checkClick(x, y)){
                setNextState(LevelOne.getInstance());
            }
            
            if(highScoreButton.checkClick(x, y)){
                //TODO: setNextState(HighScore.getInstance());
            }
            
            if(creditsButton.checkClick(x, y)){
                setNextState(Credits.getInstance());
            }
            
            if(exitButton.checkClick(x, y)){
                gc.exit();
            }
        }
        
    }

}
