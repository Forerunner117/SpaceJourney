package spaceExplorer;

import static spaceExplorer.SpaceExplorer.HEIGHT;
import static spaceExplorer.SpaceExplorer.WIDTH;
import static org.newdawn.slick.Input.KEY_UP;
import static org.newdawn.slick.Input.KEY_DOWN;
import static org.newdawn.slick.Input.KEY_LEFT;
import static org.newdawn.slick.Input.KEY_RIGHT;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

/** A class that knows about all the planets and can calculate the next point
 * taking into account the acceleration due to the gravitational pull of all the
 * planets.
 * 
 * @author Chris */
public class PhysicsEngine implements SpaceEnvironment {
    /** Space is 72x bigger then the screen. */
    private static final double spaceFieldConstant = 72;
    private static final double GRAV_CONST = 10;
    private double keyAccel = .1;
    /** The width of space */
    public static final double SPACEWIDTH = spaceFieldConstant * WIDTH;
    /** The Height of Space */
    public static final double SPACEHEIGHT = spaceFieldConstant * HEIGHT;
    private final int simtime = 1;
    private double tacoAngle;
    List<Planet> planets;

    /** Construct a physics engine. Initializes Planets. */
    public PhysicsEngine() {
        planets = new ArrayList<>();
    }

    /** Establishes a planet in the physics engine.
     * 
     * @param x x position of the planet in pixels
     * @param y y position of the planet in pixels
     * @param mass Mass of the planet range between 1-15 works best. */
    @Override
    public void createPlanet(double x, double y, double mass) {
        planets.add(new Planet(toWorldX(x), toWorldY(y), mass));
    }

    /** This registers a force on the sprite making it move.
     * 
     * @param key up down left right */
    @Override
    public void addKeyForce(int key) {
        GameModel gm = GameModel.getInstance();
        double yAccel = gm.getyAccel();
        double xAccel = gm.getxAccel();
        switch (key) {
        case KEY_UP:
            gm.setyAccel(yAccel += keyAccel);
            break;
        case KEY_DOWN:
            gm.setyAccel(yAccel -= keyAccel);
            break;
        case KEY_LEFT:
            gm.setxAccel(xAccel -= keyAccel);
            break;
        case KEY_RIGHT:
            gm.setxAccel(xAccel += keyAccel);
            break;
        default:
            // Ignore other keys
            break;
        }
    }

    /** This applies the force of all the planets on the sprite and moves it.
     * 
     * @param gm The game model. */
    @Override
    public void moveSprite(GameModel gm) {

        double y = gm.getY();
        double x = gm.getX();
        double vx = gm.getxVelocity();
        double vy = gm.getyVelocity();
        double xAccel = gm.getxAccel();
        double yAccel = gm.getyAccel();

        for (Planet p : planets) {
            xAccel += accx(x, p.getPlanetX(), p.getPlanetMass());
            yAccel += accy(y, p.getPlanetY(), p.getPlanetMass());
        }

        vx += simtime * xAccel;
        vy += simtime * yAccel;

        y += vy * simtime;
        x += vx * simtime;

        gm.setxVelocity(vx);
        gm.setyVelocity(vy);
        gm.setxAccel(xAccel);
        gm.setyAccel(yAccel);
        gm.setCoords(x, y);

    }

    /** Calculates acceleration due to gravity of the star on the the object in
     * the x direction.
     * 
     * @param x object's x position
     * @param starX - star's x coordinate
     * @param mass - mass of the star
     * @return acceleration due to gravity along the x axis */
    private double accx(double x, double starX, double mass) {
        double dx = (starX - x);
        if (Math.abs(dx) < 1) {
            return 0;
        }
        return GRAV_CONST * mass / dx;
    }

    /** Calculates acceleration due to gravity of the star on the the object in
     * the y direction.
     * 
     * @param y object's y position
     * @param starY - star's y coordinate
     * @param mass - mass of the star
     * @return acceleration due to gravity along the y axis */
    private double accy(double y, double starY, double mass) {
        double dy = starY - y;
        if (Math.abs(dy) < 1) {
            return 0;
        }
        return GRAV_CONST * mass / dy;
    }

    /** Set the initial position of the taco
     * 
     * @param x x position in pixels
     * @param y y position in pixels */
    public void registerTaco(double x, double y) {
        GameModel.getInstance().setTacoLocation(toWorldX(x), toWorldY(y));
    }

    /** Make the taco move in a circle.
     * 
     * @param model The game model */
    public void orbitTaco(GameModel model) {
        double x = model.getTacoX();
        double y = model.getTacoY();
        int length = 1000;
        double angle_stepsize = 0.07;

        // calculate x, y from a vector with known length and angle
        x += length * Math.cos(tacoAngle);
        y += length * Math.sin(tacoAngle);
        tacoAngle += angle_stepsize;
        model.setTacoLocation(x, y);
    }

    /** Eliminates planets from the physics engine. */
    @Override
    public void clearPlanets() {
        planets.clear();
    }

    /** Converts an x coordinate from pixel to world
     * 
     * @param pixelCoord X position in pixels
     * @return The world coordinate */
    private double toWorldX(double pixelCoord) {
        double ratio = (SPACEWIDTH * 2) / (WIDTH);
        double result = (pixelCoord) * ratio - SPACEWIDTH;
        return result;
    }

    /** Converts a y coordinate from pixel to world
     * 
     * @param pixelCoord Y position in pixels
     * @return The world coordinate. */
    private double toWorldY(double pixelCoord) {
        double ratio = (SPACEHEIGHT * 2) / (HEIGHT);
        double result = (pixelCoord) * ratio - SPACEHEIGHT;
        return -result;
    }

    /**  Displays stats in the game window.
     * 
     * @param g */
    public void displayStats(Graphics g) {
        GameModel gm = GameModel.getInstance();
        g.drawString("X Velocity: " + gm.getxVelocity(), 20, 20);
        g.drawString("Y Velocity: " + gm.getyVelocity(), 20, 40);
        g.drawString(
                "Sprite Position: " + gm.getPixelX() + " " + gm.getPixelY(),
                20, 80);
        g.drawString("X Acceleration: " + gm.getxAccel(), 20, 100);
        g.drawString("Y Acceleration: " + gm.getyAccel(), 20, 120);
    }

}
