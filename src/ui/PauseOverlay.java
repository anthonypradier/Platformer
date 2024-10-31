package ui;

import entities.GameState;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE;

public class PauseOverlay {
    private BufferedImage aBackgroundImg;
    private SoundButton aMusicButton, aSFXButton;
    private int aBgX, aBgY, aBgW, aBgH;

    public PauseOverlay() {
        this.loadBackground();
        this.createSoundButtons();
    }

    private void createSoundButtons() {
        int vSoundX = (int)(450 * Game.SCALE);
        int vMusicY = (int)(135 * Game.SCALE);
        int vSFXY = (int)(180 * Game.SCALE);
        this.aMusicButton = new SoundButton(vSoundX, vMusicY, SOUND_SIZE, SOUND_SIZE);
        this.aSFXButton = new SoundButton(vSoundX, vSFXY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        this.aBackgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        this.aBgW = (int)(this.aBackgroundImg.getWidth() * Game.SCALE);
        this.aBgH = (int)(this.aBackgroundImg.getHeight() * Game.SCALE);
        this.aBgX = (int)(Game.GAME_WIDTH / 2 - this.aBgW / 2);
        this.aBgY = (int)(20 * Game.SCALE);
    }

    public void update() {
        this.aMusicButton.update();
        this.aSFXButton.update();
    }

    public void draw(final Graphics pG) {
        // Background
        pG.drawImage(this.aBackgroundImg, this.aBgX, this.aBgY, this.aBgW, this.aBgH, null);

        // Buttons
        this.aMusicButton.draw(pG);
        this.aSFXButton.draw(pG);
    }

    public void mouseClicked(final MouseEvent e) {

    }

    public void mousePressed(final MouseEvent e) {
        if(this.isIn(e, this.aMusicButton)) {
            this.aMusicButton.setMousePressed(true);
        }
        if(this.isIn(e, this.aSFXButton)) {
            this.aSFXButton.setMousePressed(true);
        }
    }

    public void mouseReleased(final MouseEvent e) {
        if(this.isIn(e, this.aMusicButton)) {
            this.aMusicButton.setMuted(!this.aMusicButton.isMuted());
            this.aMusicButton.setMousePressed(false);
        }
        if(this.isIn(e, this.aSFXButton)) {
            this.aSFXButton.setMuted(!this.aSFXButton.isMuted());
            this.aSFXButton.setMousePressed(false);
        }

        // unpress the button and un-over the button if we release the cursor outside the button we pressed
        if(this.aMusicButton.isMousePressed() && !this.isIn(e, this.aMusicButton)) {
            this.aMusicButton.setMousePressed(false);
            this.aMusicButton.setMouseOver(false);
        }
        if(this.aSFXButton.isMousePressed() && !this.isIn(e, this.aSFXButton)) {
            this.aSFXButton.setMousePressed(false);
            this.aSFXButton.setMouseOver(false);
        }
    }

    public void mouseMoved(final MouseEvent e) {
        this.aMusicButton.setMouseOver(false);
        this.aSFXButton.setMouseOver(false);

        if(this.isIn(e, this.aMusicButton)) {
            this.aMusicButton.setMouseOver(true);
        }
        if(this.isIn(e, this.aSFXButton)) {
            this.aSFXButton.setMouseOver(true);
        }
    }

    public void mouseDragged(final MouseEvent e) {

    }

    private boolean isIn(final MouseEvent e, final PauseButton pButton) {
        return (pButton.getBounds().contains(e.getX(), e.getY()));
    }
}
