package spaceExplorer;

import static spaceExplorer.SpaceExplorer.HEIGHT;
import static spaceExplorer.SpaceExplorer.WIDTH;
import static spaceExplorer.PhysicsEngine.SPACEHEIGHT;
import static spaceExplorer.PhysicsEngine.SPACEWIDTH;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;

/** A class that knows various aspects of the game.
 * 
 * @author Chris */
public class GameModel {
    // May add player, map, ect.
    private double x = -SPACEWIDTH, y = 0;
    private static GameModel instance = null;
    private int spriteSpeed = 30;
    private double xAccel = 0;
    private double yAccel = 0;
    private double xVelocity = 0;
    private double yVelocity = 0;
    private double tacoX;
    private double tacoY;

    /** Get singleton instance of a game model.
     * 
     * @return singleton GameModel instance. */
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    // TODO get rid of the method.
    /** Set sprite x y coordinates in pixels.
     * 
     * @param x2 - x location in pixels
     * @param y2 - y location in pixels */
    public void setCoords(double x, double y) {
        // TODO perform boundary checking
        if ((y > SPACEHEIGHT || y < -SPACEHEIGHT)
                || (x > SPACEWIDTH || x < -SPACEWIDTH)) {
            xVelocity = 0;
            yVelocity = 0;
            xAccel = 0;
            yAccel = 0;
            return;
        }
        this.y = y;
        this.x = x;
    }

    /** Set the taco location in world coordinates.
     * 
     * @param x taco x
     * @param y taco y */
    public void setTacoLocation(double x, double y) {
        tacoX = x;
        tacoY = y;
    }

    /** @return the tacoX. */
    public double getTacoX() {
        return tacoX;
    }

    /** @return the tacoY. */
    public double getTacoY() {
        return tacoY;
    }

    /** @param xAccel the xAccel to set. */
    public void setxAccel(double xAccel) {
        this.xAccel = xAccel;
    }

    /** @param yAccel the yAccel to set. */
    public void setyAccel(double yAccel) {
        this.yAccel = yAccel;
    }

    /** @return the xAccel. */
    public double getxAccel() {
        return xAccel;
    }

    /** @return the yAccel. */
    public double getyAccel() {
        return yAccel;
    }

    /** @return the xVelocity. */
    public double getxVelocity() {
        return xVelocity;
    }

    /** @param vx the xVelocity to set. */
    public void setxVelocity(double vx) {
        this.xVelocity = vx;
    }

    /** @return the yVelocity. */
    public double getyVelocity() {
        return yVelocity;
    }

    /** @param yVelocity the yVelocity to set. */
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    /** @return Sprite x position. */
    public double getX() {
        return x;
    }

    /** @return Sprite y position. */
    public double getY() {
        return y;
    }

    /** @return the pixel x position of the sprite */
    public int getPixelX() {
        return toPixelX(x);
    }

    /** @return the pixel y position of the sprite */
    public int getPixelY() {
        return toPixelY(y);
    }

    /** @return the pixel x position of the taco */
    public int getTacoPixelX() {
        return toPixelX(tacoX);
    }

    /** @return the pixel y position of the taco */
    public int getTacoPixelY() {
        return toPixelY(tacoY);
    }

    /** @return the spriteSpeed. */
    public int getSpriteSpeed() {
        return spriteSpeed;
    }

    /** Resets the sprite position and velocity */
    public void resetSprite() {
        x = -SPACEWIDTH;
        y = 0;
        xVelocity = 0;
        yVelocity = 0;
        xAccel = 0;
        yAccel = 0;
    }

    /** Converts space coordinate to pixel coordinate.
     * 
     * @param worldCoord - coordinate in space
     * @return the pixel coordinate. */
    private int toPixelX(double worldCoord) {

        // ratio is variable that take world coordinates
        // an creates a value proportional in pixels
        double ratio = (WIDTH) / (2 * SPACEWIDTH);
        double result = (worldCoord + SPACEWIDTH) * ratio;
        return (int) result;// the int gets rid of the warning.
    }

    /** Converts space coordinate to pixel coordinate.
     * 
     * @param worldCoord - coordinate in space
     * @return the pixel coordinate. */
    private int toPixelY(double worldCoord) {

        // ratio is variable that take world coordinates
        // an creates a value proportional in pixels
        double ratio = (HEIGHT) / (2 * SPACEHEIGHT);
        double result = (worldCoord - SPACEHEIGHT) * ratio;
        return (int) -result;
    }

    /** Time to sleep the thread. Useful for slowing down sprite movement.
     * 
     * @param fps - Current frames per second. */
    public void sleep(int fps) {
        int delay = fps / spriteSpeed;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Do not Construct */
    private GameModel() {
    }

    public boolean hasTaco(Graphics g) {
        int delta = 9000;
        int width = 5040;
        int height = 3240;
        int offset = 6000;
        Rectangle goalZone = new Rectangle((int) tacoX, (int) tacoY,
                width, height);
        g.drawRect((float)tacoX, (float)tacoY, 70, 52);
//        Rectangle goalZone = new Rectangle((int) tacoX, (int) tacoY - offset,
//                delta, delta);
        return goalZone.contains(x, y);
    }
}
