package spaceExplorer;

/** A class that knows various aspects of the game.
 * 
 * @author Chris */
public class GameModel {
    // May add player, map, ect.
    private int x, y;
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

    /** Set sprite x y coordinates in pixels.
     * 
     * @param x - x location in pixels
     * @param y - y location in pixels */
    public void setCoords(int x, int y) {
        if (x > SpaceExplorer.WIDTH - Level.SPRITEWIDTH || x < 0) {
            return;
        }
        if (y > SpaceExplorer.HEIGHT - Level.SPRITEHEIGHT || y < 0) {
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
    public int getX() {
        return x;
    }

    /** @return Sprite y position. */
    public int getY() {
        return y;
    }

    /** @return the spriteSpeed. */
    public int getSpriteSpeed() {
        return spriteSpeed;
    }

    /** @param spriteSpeed the spriteSpeed to set. */
    public void setSpriteSpeed(int spriteSpeed) {
        this.spriteSpeed = spriteSpeed;
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
