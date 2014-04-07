package spaceExplorer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

// Should update automatically

public class Level extends State {
    private static Level instance = null;
    private State nextState;
    private Image[] astronautMan = new Image[12];
    private Image currentAstronaut;

    public static Level getInstance() {
        if (instance == null) {
            instance = new Level();
        }
        return instance;
    }

    private Level() {
        nextState = this;
    }

    @Override
    public State nextState() {
        return nextState;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        astronautMan[0] = new Image("astronautMan/frontStand.png");
        astronautMan[1] = new Image("astronautMan/forLeft.png");
        astronautMan[2] = new Image("astronautMan/forRight.png");
        astronautMan[3] = new Image("astronautMan/rightStand.png");
        astronautMan[4] = new Image("astronautMan/rightRight.png");
        astronautMan[5] = new Image("astronautMan/rightLeft.png");
        astronautMan[6] = new Image("astronautMan/leftStand.png");
        astronautMan[7] = new Image("astronautMan/leftRight.png");
        astronautMan[8] = new Image("astronautMan/leftStand.png");
        astronautMan[9] = new Image("astronautMan/backStand.png");
        astronautMan[10] = new Image("astronautMan/backRight.png");
        astronautMan[11] = new Image("astronautMan/backStand.png");
        currentAstronaut = astronautMan[0];
    }

    @Override
    public void render(GameContainer gc, Graphics g, GameModel model) {
        g.drawImage(currentAstronaut, model.getX(), model.getY());
    }

    @Override
    public void update(GameContainer gc, int delta, GameModel model) {
        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_UP)) {
            currentAstronaut = astronautMan[9];
            model.setCoords(model.getX(), model.getY() - 1);
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            currentAstronaut = astronautMan[6];
            model.setCoords(model.getX() - 1, model.getY());
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            currentAstronaut = astronautMan[3];
            model.setCoords(model.getX() + 1, model.getY());
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            currentAstronaut = astronautMan[0];
            model.setCoords(model.getX(), model.getY() + 1);
        }
    }

}
