package spaceExplorer;

import static spaceExplorer.SpaceExplorer.HEIGHT;
import static spaceExplorer.SpaceExplorer.WIDTH;
import static spaceExplorer.PhysicsEngine.SPACEHEIGHT;
import static spaceExplorer.PhysicsEngine.SPACEWIDTH;

import java.awt.Rectangle;

/** A class that knows various aspects of the game.
 * 
 * @author Chris */
public class GameModel {
    // May add player, map, ect.
    private double x = -SPACEWIDTH, y = 0;
    private static GameModel instance = null;
    private String playerName;
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

    /** Sets the player name.
     * 
     * @param player - the player name to set */
    public void setPlayerName(String player) {
        playerName = player;
    }

    /** Set sprite x y coordinates.
     * 
     * @param x - x location in world coords
     * @param y - y location in world coords */
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

    /** @return the taco X position in world. */
    public double getTacoX() {
        return tacoX;
    }

    /** @return the taco Y position in world. */
    public double getTacoY() {
        return tacoY;
    }

    /** @param xAccel the x Acceleration of the sprite to set. */
    public void setxAccel(double xAccel) {
        this.xAccel = xAccel;
    }

    /** @param yAccel the y Acceleration of the sprite to set. */
    public void setyAccel(double yAccel) {
        this.yAccel = yAccel;
    }

    /** @return the x Acceleration of the sprite. */
    public double getxAccel() {
        return xAccel;
    }

    /** @return the y Acceleration of the sprite. */
    public double getyAccel() {
        return yAccel;
    }

    /** @return the x Velocity of the sprite. */
    public double getxVelocity() {
        return xVelocity;
    }

    /** @param vx the x Velocity of the sprite to set. */
    public void setxVelocity(double vx) {
        this.xVelocity = vx;
    }

    /** @return the y Velocity of the sprite. */
    public double getyVelocity() {
        return yVelocity;
    }

    /** @param yVelocity the y Velocity of the sprite set. */
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

    /** Resets the sprite position, velocity, and acceleration*/
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
        double ratio = (WIDTH) / (2 * SPACEWIDTH);
        double result = (worldCoord + SPACEWIDTH) * ratio;
        return (int) result;
    }

    /** Converts space coordinate to pixel coordinate.
     * 
     * @param worldCoord - coordinate in space
     * @return the pixel coordinate. */
    private int toPixelY(double worldCoord) {
        double ratio = (HEIGHT) / (2 * SPACEHEIGHT);
        double result = (worldCoord - SPACEHEIGHT) * ratio;
        return (int) -result;
    }

    /** @return true if sprite reached the goal, false if not. */
    public boolean hasTaco() {
        int tacoWidth = 10512;
        int tacoHeight = 6768;
        Rectangle goalZone = new Rectangle((int) tacoX, (int) tacoY
                - tacoHeight, tacoWidth, tacoHeight);
        return goalZone.contains(x, y);
    }
    
    /** Do not Construct */
    private GameModel() {
    }
}
