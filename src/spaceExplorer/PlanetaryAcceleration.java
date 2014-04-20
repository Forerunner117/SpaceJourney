package spaceExplorer;

import static spaceExplorer.SpaceExplorer.HEIGHT;
import static spaceExplorer.SpaceExplorer.WIDTH;

import java.awt.Point;

/** A class that knows about all the planets and can calculate the next point
 * taking into account the acceleration due to the gravitational pull of all the
 * planets.
 * 
 * @author Chris */
public class PlanetaryAcceleration {
    private int planetXpos;
    private int planetYpos;
    private double planetMass;
    private static final double G = 6.673e-11;
    private final double spaceFieldWidth = 5.0e8 * WIDTH;
    private final double spaceFieldHeight = 5.0e8 * HEIGHT;
    private final int simtime = 1;

    /** @param x
     * @param y */
    public PlanetaryAcceleration(int x, int y) {
        planetXpos = x;
        planetYpos = y;
    }

    /** Using the mass of the planet, velocity, and the acceleration to gravity,
     * calculate the next point the object will go in pixels.
     * 
     * @param gm The game model.
     * @return the next x, y coordinates the object will be. */
    public Point nextPoint(GameModel gm) {

        // px and py are the pixel coordinates of the ship.
        int px, py;
        int y = gm.getY();
        int x = gm.getX();
        double vx = gm.getxVelocity();
        double vy = gm.getyVelocity();

        y += vy * simtime;
        x += vx * simtime;

        vx += simtime * accx(x, y, planetXpos, planetYpos, planetMass);
        vy += simtime * accy(x, y, planetXpos, planetYpos, planetMass);

        px = toPixel(x, -spaceFieldWidth, spaceFieldWidth, 0, WIDTH);
        py = toPixel(y, spaceFieldHeight, -spaceFieldHeight, 0, HEIGHT);

        gm.setxVelocity(vx);
        gm.setyVelocity(vy);

        return new Point(px, py);
    }

    /** Calculates acceleration due to gravity of the star on the the object in
     * the x direction.
     * 
     * @param x object's x position
     * @param y object's y position
     * @param starX - star's x coordinate
     * @param starY - star's y coordinate
     * @param mass - mass of the star
     * @return acceleration due to gravity along the x axis */
    private double accx(double x, double y, double starX, double starY,
            double mass) {
        double dx = (starX - x);
        double dy = (starY - y);
        double denominator = Math.pow(dx * dx + dy * dy, 1.5);
        return G * mass * dx / denominator;
    }

    /** Calculates acceleration due to gravity of the star on the the object in
     * the y direction.
     * 
     * @param x object's x position
     * @param y object's y position
     * @param starX - star's x coordinate
     * @param starY - star's y coordinate
     * @param mass - mass of the star
     * @return acceleration due to gravity along the y axis */
    private double accy(double x, double y, double starX, double starY,
            double mass) {
        double dx = (starX - x);
        double dy = (starY - y);
        double denominator = Math.pow(dx * dx + dy * dy, 1.5);
        return G * mass * dy / denominator;
    }

    /** Converts space coordinates to pixels
     * 
     * @param wx
     * @param wmin
     * @param wmax
     * @param pmin
     * @param pmax
     * @return */
    private int toPixel(double worldCoord, double worldMin, double worldMax,
            int pixelMin, int pixelMax) {

        // ratio is variable that take world coordinates
        // an creates a value proportional in pixels
        double ratio;
        double result;
        ratio = (pixelMax - pixelMin) / (worldMax - worldMin);
        result = (worldCoord - worldMin) * ratio + pixelMin;
        return (int) result;// the int gets rid of the warning.
    }
}
