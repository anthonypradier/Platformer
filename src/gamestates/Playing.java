package gamestates;

import entities.EnemyManager;
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
import java.util.Random;

import static utilz.Constants.Environment.*;

public class Playing extends State implements StateMethods {
    private Player aPlayer;
    private LevelManager aLevelManager;
    private EnemyManager aEnemyManager;
    private PauseOverlay aPauseOverlay;
    private boolean aPaused = false;

    private int aXLvlOffset; // position du player dans le monde
    private int aLeftBorder = (int)(0.2*Game.GAME_WIDTH);
    private int aRightBorder = (int)(0.8*Game.GAME_WIDTH);
    private int aLvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int aMaxTileOffset = aLvlTilesWide - Game.TILE_IN_WIDTH;
    private int aMaxLvlOffsetX = aMaxTileOffset * Game.TILE_SIZE;
    private BufferedImage aBGImg, aBigClouds, aSmallClouds;

    private int[] aSmallCloudsPos;
    private Random aRdm = new Random();


    public Playing(final Game pGame) {
        super(pGame);
        this.initClasses();

        this.aBGImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);
        this.aBigClouds = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
        this.aSmallClouds = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
        this.aSmallCloudsPos = new int[8];

        for(int i = 0; i < this.aSmallCloudsPos.length; i++) {
            this.aSmallCloudsPos[i] = (int)(90 * Game.SCALE) + this.aRdm.nextInt((int)(50*Game.SCALE), (int)(100 * Game.SCALE));
        }
    }

    private void initClasses() {
        this.aLevelManager = new LevelManager(this.aGame);
        this.aEnemyManager = new EnemyManager(this);
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
            this.aEnemyManager.update();
            this.checkCloseToBorder();
        } else {
            this.aPauseOverlay.update();
        }
    }

    @Override
    public void draw(Graphics pG) {
        pG.drawImage(this.aBGImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        this.drawClouds(pG);
        this.aLevelManager.draw(pG, this.aXLvlOffset);
        this.aPlayer.render(pG, this.aXLvlOffset);
        this.aEnemyManager.draw(pG, this.aXLvlOffset);

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

    private void drawClouds(final Graphics pG) {
        for(int i = 0; i < 3; i++) { // 204 heuteur à partir de laquelle l'eau est coupée
            pG.drawImage(this.aBigClouds, i * BIG_CLOUDS_WIDTH - (int)(this.aXLvlOffset * 0.3), (int)(204*Game.SCALE), BIG_CLOUDS_WIDTH, BIG_CLOUDS_HEIGHT, null);
        }
        for(int i = 0; i < this.aSmallCloudsPos.length; i++) {

            pG.drawImage(this.aSmallClouds, SMALL_CLOUDS_WIDTH * 4 * i - (int)(this.aXLvlOffset * .4), this.aSmallCloudsPos[i], SMALL_CLOUDS_WIDTH, SMALL_CLOUDS_HEIGHT, null);
        }//  - (int)(this.aXLvlOffset * .5) pour faire bouger le bg en même temps que le joueur
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
