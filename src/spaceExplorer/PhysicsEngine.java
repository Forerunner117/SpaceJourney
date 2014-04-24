package spaceExplorer;

import static spaceExplorer.SpaceExplorer.HEIGHT;
import static spaceExplorer.SpaceExplorer.WIDTH;

import java.awt.Point;

import org.newdawn.slick.Graphics;

/** A class that knows about all the planets and can calculate the next point
 * taking into account the acceleration due to the gravitational pull of all the
 * planets.
 * 
 * @author Chris */
public class PhysicsEngine {
    /** Space is 72x bigger then the screen. */
    private static final double spaceFieldConstant = 72;
    private final double planetXpos;
    private final double planetYpos;
    private double planetMass;
    private static final double G = 6.673e-11;
    /** The width of space */
    public static final double SPACEWIDTH = spaceFieldConstant * WIDTH;
    /** The Height of Space*/
    public static final double SPACEHEIGHT = spaceFieldConstant * HEIGHT;
    private final int simtime = 1;
    private double xAccel;
    private double yAccel;
    private double sy, sx;

    public static void main(String[] args) {
        PhysicsEngine pa = new PhysicsEngine(0, 0, 5.0);
        System.out.println("Window is 900 x 700");
        int[] c = new int[6];
        c[0] = 0;
        c[1] = 450;
        c[2] = 900;
        c[3] = 122;
        c[4] = 755;
        c[5] = 23;
        pa.printTest(c);
        c[0] = 0;
        c[1] = 350;
        c[2] = 700;
        c[3] = 122;
        c[4] = 654;
        c[5] = 23;
        pa.printTest(c);

        // System.out.println("0,0 to pixel is " + pa.toPixelX(0) + " " +
        // pa.toPixelY());
    }

    private void printTest(int[] c) {
        for (int i = 0; i < c.length; i++) {
            double world = toWorldX(c[i]);
            System.out.println("x=" + c[i] + ".  worldX= " + world
                    + ".  Back to pixels: x=" + toPixelX(world));
        }
        System.out.println("\n\n");
        for (int i = 0; i < c.length; i++) {
            double world = toWorldY(c[i]);
            System.out.println("y=" + c[i] + ".  worldY= " + world
                    + ".  Back to pixels: y=" + toPixelY(world));
        }
    }

    /** @param x
     * @param y */
    public PhysicsEngine(int x, int y, double planetMass) {
        planetXpos = toWorldX(x);
        planetYpos = toWorldY(y);
        this.planetMass = planetMass;
    }


    
    /** Using the mass of the planet, velocity, and the acceleration to gravity,
     * calculate the next point the object will go in pixels.
     * 
     * @param gm The game model.
     * @return the next x, y coordinates the object will be. */
    public Point nextPoint(GameModel gm) {

        double y = gm.getY();
        double x = gm.getX();
        double vx = gm.getxVelocity();
        double vy = gm.getyVelocity();

        xAccel = accx(x, y, planetXpos, planetYpos, planetMass);
        yAccel = accy(x, y, planetXpos, planetYpos, planetMass);

        vx += simtime * xAccel;
        vy += simtime * yAccel;

        sy += vy * simtime;
        sx += vx * simtime;

        gm.setxVelocity(vx);
        gm.setyVelocity(vy);

        gm.setCoords(x, y);
        return new Point(x, y);
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

    
    public void displayStats(Graphics g) {
        GameModel gm = GameModel.getInstance();
        g.drawString("X Velocity: " + gm.getxVelocity(), 20, 20);
        g.drawString("Y Velocity: " + gm.getyVelocity(), 20, 40);
        g.drawString("Planet Position: " + planetXpos + " " + planetYpos, 20,
                60);
        g.drawString("Sprite Position: " + gm.getX() + " " + gm.getY(), 20, 80);
        g.drawString("X Acceleration: " + xAccel, 20, 100);
        g.drawString("Y Acceleration: " + yAccel, 20, 120);

    }
}
//    private double toWorldX(double pixelCoord) {
//
//        double ratio = (SPACEWIDTH * 2) / (WIDTH);
//        double result = (pixelCoord) * ratio - SPACEWIDTH;
//        return result;
//    }
//
//    private double toWorldY(double pixelCoord) {
//        // Ratio might be negative too.
//        double ratio = (SPACEHEIGHT * 2) / (HEIGHT);
//        double result = (pixelCoord) * ratio - SPACEHEIGHT;
//        return -result;
//    }
