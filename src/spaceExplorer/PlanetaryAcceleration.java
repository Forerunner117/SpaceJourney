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
    private static final double spaceFieldConstant = 5.0e8;
    private final double planetXpos;
    private final double planetYpos;
    private double planetMass;
    private static final double G = 6.673e-11;
    private final double spaceFieldMaxWidth = spaceFieldConstant * WIDTH;
    private final double spaceFieldMaxHeight = spaceFieldConstant * HEIGHT;
    private final int simtime = 5000;

//    public static void main(String[] args) {
//        PlanetaryAcceleration pa = new PlanetaryAcceleration(0, 0, 5.0);
//        System.out.println("Window is 900 x 700");
//        int[] c = new int[6];
//        c[0] = 0; c[1] = 450; c[2] = 900; c[3] = 122; c[4] = 755; c[5] = 23;
////        pa.printTest(c);
//        c[0] = 0; c[1] = 350; c[2] = 700; c[3] = 122; c[4] = 654; c[5] = 23;
//        pa.printTest(c);
//    }
    
    private void printTest(int[] c){
        for (int i = 0; i < c.length; i++) {
            double world = toWorldX(c[i]);
            System.out.println("x=" + c[i] + ".  worldX= "+world + ".  Back to pixels: x=" + toPixelX(world));
        }
        System.out.println("\n\n");
        for (int i = 0; i < c.length; i++) {
            double world = toWorldY(c[i]);
            System.out.println("y=" + c[i] + ".  worldY= "+world + ".  Back to pixels: y=" + toPixelY(world));
        }
    }
    
    /** @param x
     * @param y */
    public PlanetaryAcceleration(int x, int y, double planetMass) {
        planetXpos = x;//toWorld(x, 0, WIDTH, -spaceFieldMaxWidth,                spaceFieldMaxWidth);
        planetYpos = y;//toWorld(y, 0, HEIGHT, spaceFieldMaxHeight,                -spaceFieldMaxHeight);
        this.planetMass = planetMass;
    }

    /** Using the mass of the planet, velocity, and the acceleration to gravity,
     * calculate the next point the object will go in pixels.
     * 
     * @param gm The game model.
     * @return the next x, y coordinates the object will be. */
    public Point nextPoint(GameModel gm) {

        int y = gm.getY();
        int x = gm.getX();
        double vx = gm.getxVelocity();
        double vy = gm.getyVelocity();

        double sy = toWorldY(y);
        double sx = toWorldX(x);

        sy += vy * simtime;
        sx += vx * simtime;

        vx += simtime * accx(sx, sy, planetXpos, planetYpos, planetMass);
        vy += simtime * accy(sx, sy, planetXpos, planetYpos, planetMass);

        int px = toPixelX(sx);
        int py = toPixelY(sy);

        gm.setxVelocity(vx);
        gm.setyVelocity(vy);

         gm.setCoords(px, py);
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

    /** Converts space coordinate to pixel coordinate.
     * 
     * @param worldCoord - coordinate in space
     * @param worldMin - minimum space coordinate
     * @param worldMax - maximum space coordinate.
     * @param pixelMin - minimum pixel
     * @param pixelMax - maximimum pixel.
     * @return the pixel coordinate. */
    private int toPixelX(double worldCoord) {

        // ratio is variable that take world coordinates
        // an creates a value proportional in pixels
        double ratio = (WIDTH) / (2 * spaceFieldMaxWidth);
        double result = (worldCoord + spaceFieldMaxWidth) * ratio;
        return (int) result;// the int gets rid of the warning.
    }

    /** Converts space coordinate to pixel coordinate.
     * 
     * @param worldCoord - coordinate in space
     * @param worldMin - minimum space coordinate
     * @param worldMax - maximum space coordinate.
     * @param pixelMin - minimum pixel
     * @param pixelMax - maximimum pixel.
     * @return the pixel coordinate. */
    private int toPixelY(double worldCoord) {

        // ratio is variable that take world coordinates
        // an creates a value proportional in pixels
        double ratio = (HEIGHT) / (-2 * spaceFieldMaxHeight);
        double result = (worldCoord + spaceFieldMaxHeight) * ratio;
        return (int) result;
    }

    private double toWorldX(double pixelCoord) {

        double ratio = (spaceFieldMaxWidth * 2) / (WIDTH);
        double result = (pixelCoord) * ratio - spaceFieldMaxWidth;
        return result;
    }

    private double toWorldY(double pixelCoord) {
        // Ratio might be negative too.
        double ratio = (-spaceFieldMaxHeight * 2) / (HEIGHT);
        double result = (pixelCoord) * ratio - spaceFieldMaxHeight;
        return result;
    }
}
