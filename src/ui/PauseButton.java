package ui;

import main.Game;

import java.awt.*;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE;

public class PauseButton {
    protected int aX, aY, aW, aH;
    protected Rectangle aBounds;

    public PauseButton(final int pX, final int pY, final int pW, final int pH) {
        this.aX = pX;
        this.aY = pY;
        this.aW = pW;
        this.aH = pH;

        this.createBounds();
    }


    private void createBounds() {
        this.aBounds = new Rectangle(this.aX, this.aY, this.aW, this.aH);
    }

    public int getX() {
        return this.aX;
    }

    public void setX(final int pX) {
        this.aX = pX;
    }

    public int getY() {
        return this.aY;
    }

    public void setY(final int pY) {
        this.aY = pY;
    }

    public int getW() {
        return this.aW;
    }

    public void setW(final int pW) {
        this.aW = pW;
    }

    public int getH() {
        return this.aH;
    }

    public void setH(final int pH) {
        this.aH = pH;
    }

    public Rectangle getBounds() {
        return this.aBounds;
    }
}
