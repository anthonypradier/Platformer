package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow aGameWindow;
    private GamePanel aGamePanel;
    private Thread aGameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 120;

    private Player aPlayer;
    public Game() {
        this.initClasses();
        this.aGamePanel = new GamePanel(this);
        this.aGameWindow = new GameWindow(this.aGamePanel);
        this.aGamePanel.requestFocus();
        this.startGameLoop();
    }

    private void initClasses() {
        this.aPlayer = new Player(100, 100);
    }

    private void startGameLoop() {
        this.aGameThread = new Thread(this);
        this.aGameThread.start();
    }

    public void update() {
        this.aPlayer.update();
    }

    public void render(Graphics pG) {
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
}
