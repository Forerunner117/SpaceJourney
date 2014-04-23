package spaceExplorer;

import java.awt.Rectangle;

import org.newdawn.slick.Image;

/**
 * @author Austin
 *
 */
public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image buttonImage;
    private Rectangle buttonShape;
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getButtonImage() {
        return buttonImage;
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param image
     * @param actionHandler
     */
    public Button(int x, int y, int width, int height, Image buttonImage) {
        buttonShape = new Rectangle(x, y, width, height);
        this.buttonImage = buttonImage;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * @param x
     * @param y
     * @return
     */
    //where x and y define the location of the mouse cursor
    public boolean checkClick(int x, int y) {
        return buttonShape.contains(x, y);
    }   

}

