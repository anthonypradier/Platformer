package ui;

import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UrmButton extends PauseButton {
    private int aRowIndex;
    private  int aIndex;
    private BufferedImage[] aImages;
    private boolean aMouseOver, aMousePressed;

    public UrmButton(final int pX, final int pY, final int pW, final int pH, final int pRowIndex) {
        super(pX, pY, pW, pH);
        this.aRowIndex = pRowIndex;

        this.loadImages();
    }

    private void loadImages() {
        BufferedImage vTemp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS);
        this.aImages = new BufferedImage[3];

        for(int vI = 0; vI < this.aImages.length; vI++) {
            this.aImages[vI] = vTemp.getSubimage(vI * URM_DEFAULT_SIZE, this.aRowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
        }
    }

    public void update() {
        this.aIndex = 0;
        if(this.aMouseOver) {
            this.aIndex = 1;
        }
        if(this.aMousePressed) {
            this.aIndex = 2;
        }
    }

    public void draw(final Graphics pG) {
        pG.drawImage(this.aImages[this.aIndex], this.aX, this.aY, URM_SIZE, URM_SIZE, null);
    }

    public void resetBools() {
        this.aMousePressed = false;
        this.aMouseOver = false;
    }

    public boolean isMouseOver() {
        return this.aMouseOver;
    }

    public void setMouseOver(final boolean pMouseOver) {
        this.aMouseOver = pMouseOver;
    }

    public boolean isMousePressed() {
        return this.aMousePressed;
    }

    public void setMousePressed(final boolean pMousePressed) {
        this.aMousePressed = pMousePressed;
    }
}
