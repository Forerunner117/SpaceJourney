package spaceExplorer;

import java.awt.Rectangle;

import org.newdawn.slick.Image;

/** A class to represent the logic and actions of a Button.
 * 
 * @author Austin */
public class Button {
    private int x;
    private int y;
    private Image buttonImage;
    private Rectangle buttonShape;

    /** @return The x position of the button. */
    public int getX() {
        return x;
    }

    /** @return The y position of the button. */
    public int getY() {
        return y;
    }

    /** @return The image of the Button. */
    public Image getButtonImage() {
        return buttonImage;
    }

    /** Constructs a button object
     * 
     * @param x the x position of the button
     * @param y the y position of the button
     * @param width the width of the button
     * @param height height of the button
     * @param buttonImage The button image */
    public Button(int x, int y, int width, int height, Image buttonImage) {
        buttonShape = new Rectangle(x, y, width, height);
        this.buttonImage = buttonImage;
        this.x = x;
        this.y = y;
    }

    /** @param x x position of the cursor
     * @param y y position of the cursor
     * @return true if button was clicked, false if not. */
    public boolean checkClick(int x, int y) {
        return buttonShape.contains(x, y);
    }

}
