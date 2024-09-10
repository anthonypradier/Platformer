package main;

public class Game implements Runnable {
    private GameWindow aGameWindow;
    private GamePanel aGamePanel;
    private Thread aGameThread;
    private final int FPS_SET = 120;
    public Game() {
        this.aGamePanel = new GamePanel();
        this.aGameWindow = new GameWindow(this.aGamePanel);
        this.aGamePanel.requestFocus();
        this.startGameLoop();
    }

    private void startGameLoop() {
        this.aGameThread = new Thread(this);
        this.aGameThread.start();
    }

    @Override
    public void run() {
        double vTimePerFrame = 1000000000.0 / FPS_SET;
        long vLastFrame = System.nanoTime();
        long vNow = System.nanoTime();

        int vFrames = 0;
        long vLastCheck = System.currentTimeMillis();
        while(true) {
            vNow = System.nanoTime();
            if(vNow - vLastFrame >= vTimePerFrame) {
                this.aGamePanel.repaint();
                vLastFrame = vNow;
                vFrames++;
            }

            if(System.currentTimeMillis() - vLastCheck >= 1000) {
                vLastCheck = System.currentTimeMillis();

                System.out.println("FPS : " + vFrames);
                vFrames = 0;
            }
        }
    }
}
