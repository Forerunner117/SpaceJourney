package spaceExplorer;

/** A class that knows various aspects of the game.
 * 
 * @author Chris */
public class GameModel {
    // May add player, map, ect.
    private int x, y;
    private static GameModel instance = null;

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
        if(x > SpaceExplorer.WIDTH - Level.SPRITEWIDTH || x < 0) {
            return;
        }
        if(y > SpaceExplorer.HEIGHT - Level.SPRITEHEIGHT || y < 0) {
            return;
        }
        this.y = y;
        this.x = x;
    }

    /** @return Sprite x position. */
    public int getX() {
        return x;
    }

    /** @return Sprite y position. */
    public int getY() {
        return y;
    }

    /** Time to sleep the thread. Useful for slowing down sprite movement.
     * 
     * @param millis - milliseconds */
    public void sleep(int fps) {
        double secondDelay = fps/26;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Do not Construct */
    private GameModel() {
    }
}
