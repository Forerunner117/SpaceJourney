package spaceExplorer;

import static spaceExplorer.SpaceExplorer.HEIGHT;
import static spaceExplorer.SpaceExplorer.WIDTH;
import static spaceExplorer.PhysicsEngine.SPACEHEIGHT;
import static spaceExplorer.PhysicsEngine.SPACEWIDTH;

/** A class that knows various aspects of the game.
 * 
 * @author Chris */
public class GameModel {
    // May add player, map, ect.
    private double x = -SPACEWIDTH, y = 0;
    private static GameModel instance = null;
    private int spriteSpeed = 30;
    private double xVelocity = 0;
    private double yVelocity = 0;

    /** Get singleton instance of a game model.
     * 
     * @return singleton GameModel instance. */
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    //TODO get rid of the method.
    /** Set sprite x y coordinates in pixels.
     * 
     * @param x2 - x location in pixels
     * @param y2 - y location in pixels */
    public void setCoords(double x, double y) {
        //TODO perform boundary checking
     if (x > SPACEWIDTH || x < -SPACEWIDTH) {
         xVelocity = 0;
         return;
        }
        if (y > SPACEHEIGHT || y < -SPACEHEIGHT) {
            yVelocity = 0;
            return;
        }
        this.y = y;
        this.x = x;
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

    public int getPixelX() {
        return toPixelX(x);
    }
    
    public int getPixelY() {
        return toPixelY(y);
    }
    /** @return the spriteSpeed. */
    public int getSpriteSpeed() {
        return spriteSpeed;
    }

    /** @param spriteSpeed the spriteSpeed to set. */
    public void setSpriteSpeed(int spriteSpeed) {
        this.spriteSpeed = spriteSpeed;
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
}
