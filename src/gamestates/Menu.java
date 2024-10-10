package gamestates;

import entities.GameState;
import main.Game;
import ui.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.LoadSave.GetSpriteAtlas;
import static utilz.LoadSave.MENU_BACKGROUND;

public class Menu extends State implements StateMethods {
    private MenuButton[] aButtons = new MenuButton[3];
    private BufferedImage aBackgroundImg;
    private int aMenuX, aMenuY, aMenuW, aMenuH;


    public Menu(final Game pGame) {
        super(pGame);
        this.loadButtons();
        this.loadBackground();
    }

    private void loadBackground() {
        this.aBackgroundImg = GetSpriteAtlas(MENU_BACKGROUND);
        this.aMenuW = (int)(this.aBackgroundImg.getWidth() * Game.SCALE);
        this.aMenuH = (int)(this.aBackgroundImg.getHeight() * Game.SCALE);
        this.aMenuX = Game.GAME_WIDTH / 2 - this.aMenuW / 2;
        this.aMenuY = (int)(45 * Game.SCALE);
    }

    private void loadButtons() {
        this.aButtons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, GameState.PLAYING);
        this.aButtons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, GameState.OPTIONS);
        this.aButtons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, GameState.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton vButton : this.aButtons) {
            vButton.update();
        }
    }

    @Override
    public void draw(final Graphics pG) {
        pG.drawImage(this.aBackgroundImg, this.aMenuX, this.aMenuY, this.aMenuW, this.aMenuH, null);
        for(MenuButton vButton : this.aButtons) {
            vButton.draw(pG);
        }
    }

    @Override
    public void mouseClicked(final MouseEvent e) {

    }

    @Override
    public void mousePressed(final MouseEvent e) {
        for(MenuButton vButton : this.aButtons) {
            if(this.isIn(e, vButton)) {
                vButton.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        for(MenuButton vButton : this.aButtons) {
            if(this.isIn(e, vButton)) {
                if(vButton.isMousePressed()) {
                    vButton.applyGameState();
                    break;
                }
            }
        }
        this.resetButtons();
    }

    public void resetButtons() {
        for(MenuButton vButton : this.aButtons) {
            vButton.resetBooleans();
        }
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        for(MenuButton vButton : this.aButtons) {
            vButton.setMouseOver(false);
        }
        for(MenuButton vButton : this.aButtons) {
            if(this.isIn(e, vButton)) {
                vButton.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            GameState.aState = GameState.PLAYING;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {

    }
}
