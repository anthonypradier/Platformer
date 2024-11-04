package ui;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButtons.SOUND_DEFAULT_SIZE;

public class SoundButton extends PauseButton {
    private BufferedImage[][] aSoundImages;
    private boolean aMouseOver, aMousePressed;
    private boolean aMuted;
    private int aRowIndex, aColIndex;
    public SoundButton(final int pX, final int pY, final int pW, final int pH) {
        super(pX, pY, pW, pH);

        this.loadImages();
    }

    private void loadImages() {
        BufferedImage vTemp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS);
        this.aSoundImages = new BufferedImage[2][3];
        for(int vI = 0; vI < this.aSoundImages.length; vI++) {
            for(int vJ = 0; vJ < this.aSoundImages[0].length; vJ++) {
                this.aSoundImages[vI][vJ] = vTemp.getSubimage(vJ * SOUND_DEFAULT_SIZE, vI * SOUND_DEFAULT_SIZE, SOUND_DEFAULT_SIZE, SOUND_DEFAULT_SIZE);
            }
        }
    }

    public void update() {
        this.aRowIndex = 0;
        if(this.aMuted) {
            this.aRowIndex = 1;
        }

        this.aColIndex = 0;
        if(this.aMouseOver) {
            this.aColIndex = 1;
        }
        if(this.aMousePressed) {
            this.aColIndex = 2;
        }
    }

    public void draw(final Graphics pG) {
        pG.drawImage(this.aSoundImages[this.aRowIndex][this.aColIndex], this.aX, this.aY, this.aW, this.aH, null);
    }

    public void resetBools() {
        this.aMousePressed = false;
        this.aMouseOver = false;
    }

    public boolean isMouseOver() {
        return this.aMouseOver;
    }

    public void setMouseOver(final boolean aMouseOver) {
        this.aMouseOver = aMouseOver;
    }

    public boolean isMousePressed() {
        return this.aMousePressed;
    }

    public void setMousePressed(final boolean pMousePressed) {
        this.aMousePressed = pMousePressed;
    }

    public boolean isMuted() {
        return this.aMuted;
    }

    public void setMuted(final boolean pMuted) {
        this.aMuted = pMuted;
    }

    public int getColI() {
        return this.aColIndex;
    }
}
