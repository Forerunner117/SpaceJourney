package spaceExplorer;

/** A class that represents a planet in a level.
 * 
 * @author Chris */
public class Planet {
    private final double x;
    private final double y;
    private final double planetMass;

    /** @return the planetXpos. */
    public double getPlanetX() {
        return x;
    }

    /** @return the planetYpos. */
    public double getPlanetY() {
        return y;
    }

    /** @return the planetMass. */
    public double getPlanetMass() {
        return planetMass;
    }

    /** Construct a planet
     * 
     * @param x planet x position
     * @param y planet y position
     * @param mass mass of planet */
    public Planet(double x, double y, double mass) {
        this.x = x;
        this.y = y;
        planetMass = mass;
    }

}
