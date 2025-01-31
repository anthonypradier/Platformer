package gamestates;

import entities.GameState;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Playing extends State implements StateMethods {
    private Player aPlayer;
    private LevelManager aLevelManager;
    private PauseOverlay aPauseOverlay;
    private boolean aPaused = false;

    private int aXLvlOffset;
    private int aLeftBorder = (int)(0.2*Game.GAME_WIDTH);
    private int aRightBorder = (int)(0.8*Game.GAME_WIDTH);
    private int aLvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int aMaxTileOffset = aLvlTilesWide - Game.TILE_IN_WIDTH;
    private int aMaxLvlOffsetX = aMaxTileOffset * Game.TILE_SIZE;
    private BufferedImage aPlayingBG = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);


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
            this.checkCloseToBorder();
        } else {
            this.aPauseOverlay.update();
        }
    }

    @Override
    public void draw(Graphics pG) {
        pG.drawImage(this.aPlayingBG, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        this.aLevelManager.draw(pG, this.aXLvlOffset);
        this.aPlayer.render(pG, this.aXLvlOffset);

        if(this.aPaused) {
            pG.setColor(new Color(0, 0, 0, 100));
            pG.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            this.aPauseOverlay.draw(pG);
        }
    }

    private void checkCloseToBorder() {
        int vPlayerX = (int) this.aPlayer.getHitbox().getX();
        int vDiff = vPlayerX - this.aXLvlOffset;

        if (vDiff > this.aRightBorder) {
            this.aXLvlOffset += vDiff - this.aRightBorder;
        } else if(vDiff < this.aLeftBorder) {
            this.aXLvlOffset += vDiff - this.aLeftBorder;
        }
        if(this.aXLvlOffset > this.aMaxLvlOffsetX) {
            this.aXLvlOffset = this.aMaxLvlOffsetX;
        } else if(this.aXLvlOffset < 0) {
            this.aXLvlOffset = 0;
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
