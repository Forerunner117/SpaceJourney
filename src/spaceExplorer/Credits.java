package spaceExplorer;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/** A class to display the credits.
 * 
 * @author austin */
public class Credits extends State {
    private static Credits instance = null;
    private State nextState;
    private Image mainMenuImg;
    private Image exitImg;
    private TrueTypeFont trueTypeFont1;
    private TrueTypeFont trueTypeFont2;
    private TrueTypeFont trueTypeFont3;

    private Button mainMenuButton;
    private Button exitButton;

    /** @return Singleton instance of Pause Menu. */
    public static Credits getInstance() {
        if (instance == null) {
            try {
                instance = new Credits();
            } catch (SlickException e) {
                throw new RuntimeException();
            }
        }
        return instance;
    }

    /** Private constructor to initialize next state to this and load images and
     * fonts.
     * 
     * @throws SlickException */
    private Credits() throws SlickException {
        nextState = this;
        mainMenuImg = new Image("resources/buttons/mainMenuButton.png");
        exitImg = new Image("resources/buttons/exitButton.png");

        mainMenuButton = new Button(10, 620, 326, 59, mainMenuImg);
        exitButton = new Button(568, 620, 325, 59, exitImg);

        Font font = new Font("Ubuntu Medium", Font.BOLD, 50);
        trueTypeFont1 = new TrueTypeFont(font, true);

        Font font2 = new Font("Ubuntu Medium", Font.BOLD, 40);
        trueTypeFont2 = new TrueTypeFont(font2, true);

        Font font3 = new Font("Ubuntu Medium", Font.BOLD, 30);
        trueTypeFont3 = new TrueTypeFont(font3, true);

    }

    @Override
    public State nextState() {
        return nextState;
    }

    /** Sets the next state.
     * 
     * @param state the next state to set. */
    private void setNextState(State state) {
        nextState = state;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        // All happens in the constructor.
    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel gm) {
        g.drawImage(MainMenu.menuWallpaper, 0, 0);
        g.drawImage(mainMenuButton.getButtonImage(), mainMenuButton.getX(),
                mainMenuButton.getY());
        g.drawImage(exitButton.getButtonImage(), exitButton.getX(),
                exitButton.getY());

        trueTypeFont1.drawString(340.0f, 20.0f, "Credits");

        trueTypeFont2.drawString(270.0f, 150.0f, "Game Designers");
        trueTypeFont3.drawString(335.0f, 220.0f, "Chris Costello");
        trueTypeFont3.drawString(342.0f, 260.0f, "Austin Longo");

        trueTypeFont2.drawString(220.0f, 350.0f, "Inspirational Teachers");
        trueTypeFont3.drawString(340.0f, 420.0f, "Dana Hughes");
        trueTypeFont3.drawString(375.0f, 460.0f, "Liz Boese");
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        nextState = this;
        Input input = gc.getInput();

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            int x = input.getMouseX();
            int y = input.getMouseY();

            if (mainMenuButton.checkClick(x, y)) {
                setNextState(MainMenu.getInstance());
            }

            if (exitButton.checkClick(x, y)) {
                gc.exit();
            }
        }

    }

}
