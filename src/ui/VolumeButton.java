package ui;

import utilz.LoadSave;
import static utilz.Constants.UI.VolumeButtons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VolumeButton extends PauseButton {
    private int aIndex;
    private BufferedImage[] aImages;
    private BufferedImage aSlider;
    private boolean aMouseOver, aMousePressed;
    private int aButtonX;
    private int aMinX, aMaxX;

    public VolumeButton(final int pX, final int pY, final int pW, final int pH) {
        super(pX + pW / 2, pY, VOLUME_WIDTH, pH);
        /* VolumeButton comprend et le slider, et le bouton
        * la width est celle du bouton car c'est sur ce dernier que l'on créer le bound pour les mouseEvents
        * après avoir créee les bounds, on réinitialise la position initiale aX et la largeur aW
        * Si on ne le fait pas, le draw du slider ne sera de la largeur du bouton de volume
        */
        this.aBounds.x -= VOLUME_WIDTH / 2;
        this.aButtonX = pX + pW / 2;
        this.aX = pX;
        this.aW = pW;
        this.aMinX = this.aX + VOLUME_WIDTH / 2;
        this.aMaxX = this.aX + this.aW - VOLUME_WIDTH / 2;
        this.loadImages();
    }

    private void loadImages() {
        BufferedImage vTemp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
        this.aImages = new BufferedImage[3];
        for(int vI = 0; vI < this.aImages.length; vI++) {
            this.aImages[vI] = vTemp.getSubimage(vI * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
        }

        // start after the 3 volume buttons
        this.aSlider = vTemp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
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
        pG.drawImage(this.aSlider, this.aX, this.aY, this.aW, this.aH, null);
        pG.drawImage(this.aImages[this.aIndex], this.aButtonX - VOLUME_WIDTH / 2, this.aY, VOLUME_WIDTH, this.aH, null);
    }

    public void changeX(final int pX) {
        if(pX < this.aMinX) {
            this.aButtonX = this.aMinX;
        } else if(pX > this.aMaxX) {
            this.aButtonX = this.aMaxX;
        } else {
            this.aButtonX = pX;
        }
        this.aBounds.x = this.aButtonX - VOLUME_WIDTH / 2;
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
