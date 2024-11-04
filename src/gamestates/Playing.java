package gamestates;

import entities.GameState;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {
    private Player aPlayer;
    private LevelManager aLevelManager;
    private PauseOverlay aPauseOverlay;
    private boolean aPaused = false;

    public Playing(final Game pGame) {
        super(pGame);
        this.initClasses(); 
    }

    private void initClasses() {
        this.aLevelManager = new LevelManager(this.aGame);
        this.aPlayer = new Player(200, 200, (int)(Game.PLAYER_SPRITE_SIZE * Game.SCALE), (int)(Game.PLAYER_SPRITE_SIZE * Game.SCALE));
        this.aPlayer.loadLevelData(this.aLevelManager.getCurrentLevel().getLevelData());
        this.aPauseOverlay = new PauseOverlay(this);
    }


    public Player getPlayer() {
        return this.aPlayer;
    }

    public void windowFocusLost() {
        this.aPlayer.resetDirBoolean();
    }

    @Override
    public void update() {
        if(!this.aPaused) {
            this.aLevelManager.update();
            this.aPlayer.update();
        } else {
            this.aPauseOverlay.update();
        }
    }

    @Override
    public void draw(Graphics pG) {
        this.aLevelManager.draw(pG);
        this.aPlayer.render(pG);

        if(this.aPaused) {
            this.aPauseOverlay.draw(pG);
        }
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            this.aPlayer.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if(this.aPaused) {
            this.aPauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if(this.aPaused) {
            this.aPauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        if(this.aPaused) {
            this.aPauseOverlay.mouseMoved(e);
        }
    }

    public void mouseDragged(final MouseEvent e) {
        if(this.aPaused) {
            this.aPauseOverlay.mouseDragged(e);
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_Z:
//                this.aPlayer.setUp(true);
//                break;
            case KeyEvent.VK_Q:
                this.aPlayer.setLeft(true);
                break;
//            case KeyEvent.VK_S:
//                this.aPlayer.setDown(true);
//                break;
            case KeyEvent.VK_D:
                this.aPlayer.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                this.aPlayer.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                this.aPaused = !this.aPaused;
                break;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_Z:
//                this.aPlayer.setUp(false);
//                break;
            case KeyEvent.VK_Q:
                this.aPlayer.setLeft(false);
                break;
//            case KeyEvent.VK_S:
//                this.aPlayer.setDown(false);
//                break;
            case KeyEvent.VK_D:
                this.aPlayer.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                this.aPlayer.setJump(false);
                break;
        }
    }

    public void unpauseGame() {
        this.aPaused = false;
    }

    public void pauseGame() {
        this.aPaused = true;
    }
}
