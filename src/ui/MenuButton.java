package ui;

import entities.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.Buttons.*;
import static utilz.LoadSave.*;
import static utilz.LoadSave.GetSpriteAtlas;

public class MenuButton {
    private int aXPos, aYPos, aRowIndex, aIndex;
    private BufferedImage[] aImgs;
    private GameState aState;
    private int aXOffsetCenter = B_WIDTH / 2;
    private boolean aMouseOver, aMousePressed;
    private Rectangle aBounds;

    public MenuButton(final int pXPos, final int pYPos, final int pRowIndex, final GameState pState) {
        this.aXPos = pXPos;
        this.aYPos = pYPos;
        this.aRowIndex = pRowIndex;
        this.aState = pState;

        this.loadImages();
        this.initBounds();
    }

    private void loadImages() {
        this.aImgs = new BufferedImage[3];
        BufferedImage vTemp = GetSpriteAtlas(MENU_BUTTONS);
        for(int vI = 0; vI < this.aImgs.length; vI++) {
            this.aImgs[vI] = vTemp.getSubimage(vI * B_WIDTH_DEFAULT, this.aRowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    private void initBounds() {
        this.aBounds = new Rectangle(this.aXPos - this.aXOffsetCenter, this.aYPos, B_WIDTH, B_HEIGHT);
    }
    public void draw(Graphics pG) {
        pG.drawImage(this.aImgs[this.aIndex], this.aXPos - this.aXOffsetCenter, this.aYPos, B_WIDTH, B_HEIGHT, null);
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

    public void applyGameState() {
        GameState.aState = this.aState;
    }

    public void resetBooleans() {
        this.aMouseOver = false;
        this.aMousePressed = false;
    }

    public Rectangle getBounds() {
        return this.aBounds;
    }
}
