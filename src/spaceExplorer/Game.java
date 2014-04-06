package spaceExplorer;

public class Game {
    private State state;
    private GameModel gm;
    
    public Game() {
        // Will be main menu in the final product
        state = Level.getInstance();
    }
    
    public static void main(String[] args) {
        
    }
}
