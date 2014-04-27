package spaceExplorer;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 * @author austin
 *
 */
public class HighScores extends State{
    private static HighScores instance = null;
    private State nextState;    
    private Image mainMenuImg;
    private Image exitImg;
    private TrueTypeFont trueTypeFont1;
    private TrueTypeFont trueTypeFont2;
    private TrueTypeFont trueTypeFont3;
    private static String[] highScores;

    private Button mainMenuButton;
    private Button exitButton;
    
    /** @return Singleton instance of Pause Menu. */
    public static HighScores getInstance() {
        if (instance == null) {
            try {
                instance = new HighScores();
            } catch (SlickException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        highScores = HighScoreUtil.readScores();
                
        return instance;
    }

    /** Private constructor to initialize next state to this. 
     * @throws SlickException */
    private HighScores() throws SlickException {
        nextState = this; 
        mainMenuImg = new Image("resources/buttons/mainMenuButton.png");
        exitImg = new Image("resources/buttons/exitButton.png");
        
        mainMenuButton = new Button(10, 620, 326, 59, mainMenuImg);
        exitButton = new Button(568, 620, 325, 59, exitImg);
        
        Font font = new Font("Verdana", Font.BOLD, 50);
        trueTypeFont1 = new TrueTypeFont(font, true);
        
        Font font2 = new Font("Verdana", Font.BOLD, 40);
        trueTypeFont2 = new TrueTypeFont(font2, true);
        
        Font font3 = new Font("Verdana", Font.BOLD, 30);
        trueTypeFont3 = new TrueTypeFont(font3, true);
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

    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel gm) {        
        g.drawImage(MainMenu.menuWallpaper, 0, 0);
        g.drawImage(mainMenuButton.getButtonImage(), mainMenuButton.getX(), mainMenuButton.getY());
        g.drawImage(exitButton.getButtonImage(), exitButton.getX(), exitButton.getY());
        
        trueTypeFont1.drawString(340.0f, 20.0f, "High Scores");
        
        trueTypeFont2.drawString(255.0f, 150.0f, "Level 1");
        trueTypeFont3.drawString(330.0f, 220.0f, highScores[0] + "   " + highScores[1]+"sec");        
        
        trueTypeFont2.drawString(200.0f, 350.0f, "Level 2");
        trueTypeFont3.drawString(330.0f, 420.0f, highScores[2] + "   " + highScores[3]+"sec");        
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        nextState = this; 
                
        Input input = gc.getInput();
        
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {        
            int x = input.getMouseX();
            int y = input.getMouseY();                                     
            
            if (mainMenuButton.checkClick(x, y)){
                setNextState(MainMenu.getInstance());
            }
            
            if(exitButton.checkClick(x, y)){
                gc.exit();
            }
        }
        
    }

}
