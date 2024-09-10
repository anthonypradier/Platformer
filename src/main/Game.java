package main;

public class Game {
    private GameWindow aGameWindow;
    private GamePanel aGamePanel;
    public Game() {
        this.aGamePanel = new GamePanel();
        this.aGameWindow = new GameWindow(this.aGamePanel);
    }
}
