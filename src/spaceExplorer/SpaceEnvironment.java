package spaceExplorer;

/** Defines the behavior of a class that governs the physics and movement in
 * space
 * 
 * @author Chris */
public interface SpaceEnvironment {

    /** Create a new Planet
     * 
     * @param x the x position of the planet in pixels
     * @param y the y position of the planet in pixels
     * @param mass mass of the planet. */
    public abstract void createPlanet(double x, double y, double mass);

    /** Given a key: up, down, left, right, this applies a force to the sprite to
     * move it.
     * 
     * @param key up, down, left, right */
    public abstract void addKeyForce(int key);

    /** Using the mass of the planet, velocity, and the acceleration to gravity,
     * calculate the next point the object will go in pixels.
     * 
     * @param gm The game model. */
    public abstract void moveSprite(GameModel gm);

    /** Clear all planets in the space */
    public abstract void clearPlanets();

    /** Makes the taco move in a circle.
     * 
     * @param gm The game model */
    public abstract void orbitTaco(GameModel gm);

    /** Set the initial position of the taco
     * 
     * @param x x position
     * @param y y position */
    public abstract void registerTaco(double x, double y);
}