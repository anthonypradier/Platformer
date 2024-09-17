package main;

import entities.Player;
import levels.LevelManager;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow aGameWindow;
    private GamePanel aGamePanel;
    private Thread aGameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILE_IN_WIDTH = 26;
    public final static int TILE_IN_HEIGHT = 14;
    public final static int TILE_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILE_SIZE * TILE_IN_WIDTH;
    public final static int GAME_HEIGHT = TILE_SIZE * TILE_IN_HEIGHT;

    private Player aPlayer;
    public static final int PLAYER_SPRITE_SIZE = 96;
    private LevelManager aLevelManager;

    public Game() {
        this.initClasses();
        this.aGamePanel = new GamePanel(this);
        this.aGameWindow = new GameWindow(this.aGamePanel);
        this.aGamePanel.requestFocus();
        this.startGameLoop();
    }

    private void initClasses() {
        this.aLevelManager = new LevelManager(this);
        this.aPlayer = new Player(200, 200, (int)(PLAYER_SPRITE_SIZE * SCALE), (int)(PLAYER_SPRITE_SIZE * SCALE));
        this.aPlayer.loadLevelData(this.aLevelManager.getCurrentLevel().getLevelData());
    }

    private void startGameLoop() {
        this.aGameThread = new Thread(this);
        this.aGameThread.start();
    }

    public void update() {
        this.aLevelManager.update();
        this.aPlayer.update();
    }

    public void render(Graphics pG) {
        this.aLevelManager.draw(pG);
        this.aPlayer.render(pG);
    }

    @Override
    public void run() {
        double vTimePerFrame = 1000000000.0 / FPS_SET;
        double vTimePerUpdate = 1000000000.0 / UPS_SET;

        long vPreviousTime = System.nanoTime();

        int vFrames = 0;
        int vUpdates = 0;
        long vLastCheck = System.currentTimeMillis();

        double vDeltaU = 0;
        double vDeltaF = 0;

        while(true) {
            long vCurrentTime = System.nanoTime();

            vDeltaU += (vCurrentTime - vPreviousTime) / vTimePerUpdate;
            vDeltaF += (vCurrentTime - vPreviousTime) / vTimePerFrame;
            vPreviousTime = vCurrentTime;

            if(vDeltaU >= 1) {
                this.update();
                vUpdates++;
                vDeltaU--;
            }

            if(vDeltaF >= 1) {
                this.aGamePanel.repaint();
                vFrames++;
                vDeltaF--;
            }

            if(System.currentTimeMillis() - vLastCheck >= 1000) {
                vLastCheck = System.currentTimeMillis();

                System.out.println("FPS : " + vFrames + " | UPS : " + vUpdates);
                vFrames = 0;
                vUpdates = 0;
            }
        }
    }

    public Player getPlayer() {
        return this.aPlayer;
    }

    public void windowFocusLost() {
        this.aPlayer.resetDirBoolean();
    }
}
