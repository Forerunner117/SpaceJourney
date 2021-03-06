package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** @author Austin */
public class PauseMenu extends State {
    private static PauseMenu instance = null;
    private State nextState;
    private Image resumeImg;
    private Image mainMenuImg;
    private Image exitImg;

    private Button resumeButton;
    private Button mainMenuButton;
    private Button exitButton;

    /** @return Singleton instance of Pause Menu. */
    public static PauseMenu getInstance() {
        if (instance == null) {
            try {
                instance = new PauseMenu();
            } catch (SlickException e) {
                throw new RuntimeException();
            }
        }
        return instance;
    }

    /** Private constructor to initialize next state to this.
     * 
     * @throws SlickException */
    private PauseMenu() throws SlickException {
        nextState = this;
        resumeImg = new Image("resources/buttons/resumeButton.png");
        mainMenuImg = new Image("resources/buttons/mainMenuButton.png");
        exitImg = new Image("resources/buttons/exitButton.png");

        resumeButton = new Button(309, 100, 283, 58, resumeImg);
        mainMenuButton = new Button(287, 300, 326, 59, mainMenuImg);
        exitButton = new Button(288, 500, 325, 59, exitImg);
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
        // All done in the constructor.
    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel gm) {
        g.drawImage(MainMenu.menuWallpaper, 0, 0);
        g.drawImage(resumeButton.getButtonImage(), resumeButton.getX(),
                resumeButton.getY());
        g.drawImage(mainMenuButton.getButtonImage(), mainMenuButton.getX(),
                mainMenuButton.getY());
        g.drawImage(exitButton.getButtonImage(), exitButton.getX(),
                exitButton.getY());
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        nextState = this;
        Input input = gc.getInput();

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            int x = input.getMouseX();
            int y = input.getMouseY();

            if (resumeButton.checkClick(x, y)) {
                setNextState(LevelOne.getInstance());
            }

            if (mainMenuButton.checkClick(x, y)) {
                setNextState(MainMenu.getInstance());
            }

            if (exitButton.checkClick(x, y)) {
                gc.exit();
            }
        }
    }
}
