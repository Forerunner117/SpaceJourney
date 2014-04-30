package spaceExplorer;

import java.io.File;
import java.util.Scanner;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.lwjgl.LWJGLUtil;

/** The Launching point for the game.
 * 
 * @author Chris */
public class SpaceExplorer extends BasicGame {

    /** The width of the window. */
    public static final int WIDTH = 900;
    /** The height of the window. */
    public static final int HEIGHT = 700;

    private State state;
    private GameModel model;

    /** Get instance of all states */
    public SpaceExplorer() {
        super("Space Explorer");
        state = MainMenu.getInstance();
        model = GameModel.getInstance();
    }

    /** Start Game and get input here
     * 
     * @param args Command line arguments */
    public static void main(String[] args) {
        // For system independence
        System.setProperty("org.lwjgl.librarypath",
                new File(new File(System.getProperty("user.dir"), "lib/lwjgl-2.9.1/native"),
                        LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath",
                System.getProperty("org.lwjgl.librarypath"));
        // Process name input.
        if (args.length < 1) {
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter your name: ");
            String playerName = reader.nextLine();
            GameModel.getInstance().setPlayerName(playerName);
            if (reader != null) {
                reader.close();
            }
        } else {
            GameModel.getInstance().setPlayerName(args[0]);
        }
        // Start the game.
        try {
            AppGameContainer app = new AppGameContainer(new SpaceExplorer());
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setShowFPS(false);
            app.setTargetFrameRate(70);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /** Gets called once before game loop
     * 
     * @param gc GameContainer that has various methods.
     * @throws SlickException */
    @Override
    public void init(GameContainer gc) throws SlickException {
        state.init(gc);
        Level.getInstance().init(gc);
        
        Music mainMusic = new Music("resources/tiesto.ogg");

        mainMusic.loop();
    }

    /** Gets called continuously in game loop. Delegates everything.
     * 
     * @param gc GameContainer for various useful methods
     * @param g Graphics object for drawing.
     * @throws SlickException */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        state.render(gc, g, model);
        state = state.nextState();
    }

    /** Gets called continuously in game loop. Updates the state which updates
     * the model.
     * 
     * @param gc Game Container.
     * @param delta - Time delay.
     * @throws SlickException */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        state.update(gc, delta, model);
    }

}
