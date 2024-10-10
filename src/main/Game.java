package main;

import entities.GameState;
import gamestates.Playing;
import gamestates.Menu;

import java.awt.Graphics;

public class Game implements Runnable {
    private final GameWindow aGameWindow;
    private final GamePanel aGamePanel;
    private Thread aGameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Playing aPlaying;
    private Menu aMenu;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILE_IN_WIDTH = 26;
    public final static int TILE_IN_HEIGHT = 14;
    public final static int TILE_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILE_SIZE * TILE_IN_WIDTH;
    public final static int GAME_HEIGHT = TILE_SIZE * TILE_IN_HEIGHT;

    public static final int PLAYER_SPRITE_SIZE = 96;

    public Game() {
        this.initClasses();
        this.aGamePanel = new GamePanel(this);
        this.aGameWindow = new GameWindow(this.aGamePanel);
        this.aGamePanel.requestFocus();
        this.startGameLoop();
    }

    private void initClasses() {
        this.aMenu = new Menu(this);
        this.aPlaying = new Playing(this);
    }

    private void startGameLoop() {
        this.aGameThread = new Thread(this);
        this.aGameThread.start();
    }

    public void update() {

        switch (GameState.aState) {
            case MENU:
                this.aMenu.update();
                break;
            case PLAYING:
                this.aPlaying.update();
                break;
            case OPTIONS:
            case QUIT:
                System.exit(0);
            default:
                break;
        }
    }

    public void render(Graphics pG) {
        switch (GameState.aState) {
            case MENU:
                this.aMenu.draw(pG);
                break;
            case PLAYING:
                this.aPlaying.draw(pG);
                break;
            default:
                break;
        }
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

    public void windowFocusLost() {
        if(GameState.aState == GameState.PLAYING) {
            this.aPlaying.getPlayer().resetDirBoolean();
        }
    }

    public Menu getMenu() {
        return this.aMenu;
    }

    public Playing getPlaying() {
        return this.aPlaying;
    }
}
