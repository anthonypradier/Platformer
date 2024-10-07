package gamestates;

import entities.GameState;
import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {
    private Player aPlayer;
    private LevelManager aLevelManager;

    public Playing(final Game pGame) {
        super(pGame);
        this.initClasses(); 
    }

    private void initClasses() {
        this.aLevelManager = new LevelManager(this.aGame);
        this.aPlayer = new Player(200, 200, (int)(Game.PLAYER_SPRITE_SIZE * Game.SCALE), (int)(Game.PLAYER_SPRITE_SIZE * Game.SCALE));
        this.aPlayer.loadLevelData(this.aLevelManager.getCurrentLevel().getLevelData());
    }


    public Player getPlayer() {
        return this.aPlayer;
    }

    public void windowFocusLost() {
        this.aPlayer.resetDirBoolean();
    }

    @Override
    public void update() {
        this.aLevelManager.update();
        this.aPlayer.update();
    }

    @Override
    public void draw(Graphics pG) {
        this.aLevelManager.draw(pG);
        this.aPlayer.render(pG);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            this.aPlayer.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
                GameState.aState = GameState.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
}
