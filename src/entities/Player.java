package entities;

import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.Directions.*;
import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity {
    private BufferedImage[][] aAnimations;
    private int aAnimTick;
    private int aAnimIndex;
    private final int aAnimSpeed = 25;
    private int aPlayerAction = IDLE;
    private int aPlayerDirection = -1;
    private boolean aMoving = false, aAttacking = false;
    private float aPlayerSpeed = 3f;

    private boolean aLeft, aUp, aRight, aDown;

    public Player(final float pX, final float pY) {
        super(pX, pY);
        this.loadAnimations();
    }

    public void update() {
        this.updatePosition();
        this.updateAnimTick();
        this.setAnimation();
    }

    public void render(final Graphics pG) {
        pG.drawImage(this.aAnimations[this.aPlayerAction][this.aAnimIndex], (int)this.aX, (int)this.aY, 192, 192, null);
    }

    private void loadAnimations() {
        BufferedImage vImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        this.aAnimations = new BufferedImage[9][6];
        for(int vJ = 0; vJ < this.aAnimations.length; vJ++) {
            for (int vI = 0; vI < this.aAnimations[vJ].length; vI++) {
                this.aAnimations[vJ][vI] = vImg.getSubimage(vI * 96, (vJ + 8) * 96, 96, 96); // Commencer les animations a partir de l'épée
            }
        }
    }

    public void setAnimation() {
        int vStartAnim = this.aPlayerAction;
        if(this.aMoving) {
            this.aPlayerAction = RUNNING;
        } else {
            this.aPlayerAction = IDLE;
        }

        if(this.aAttacking) {
            this.aPlayerAction = ATTACK;
        }

        if(this.aPlayerAction != vStartAnim) {
            this.resetAnimTick();
        }
    }

    public void resetAnimTick() {
        this.aAnimTick = 0;
        this.aAnimIndex = 0;
    }

    public void updatePosition() {
        this.aMoving = false;

        if(this.aLeft && !this.aRight) {
            this.aX -= this.aPlayerSpeed;
            this.aMoving = true;
        } else if(!this.aLeft  && this.aRight) {
            this.aX += this.aPlayerSpeed;
            this.aMoving = true;
        }

        if(this.aUp && !this.aDown) {
            this.aY -= this.aPlayerSpeed;
            this.aMoving = true;
        } else if(!this.aUp  && this.aDown) {
            this.aY += this.aPlayerSpeed;
            this.aMoving = true;
        }
    }

    private void updateAnimTick() {
        this.aAnimTick++;
        if(this.aAnimTick == this.aAnimSpeed) {
            this.aAnimIndex++;
            this.aAnimTick = 0;

            if(this.aAnimIndex >= GetSpriteAmount(this.aPlayerAction)) {
                this.aAnimIndex = 0;
                this.aAttacking = false;
            }
        }
    }

    public void resetDirBoolean() {
        this.aLeft = false;
        this.aUp = false;
        this.aRight = false;
        this.aDown = false;
    }

    public void setAttacking(final boolean pAttacking) {
        this.aAttacking = pAttacking;
    }

    public boolean isLeft() {
        return this.aLeft;
    }

    public void setLeft(boolean pLeft) {
        this.aLeft = pLeft;
    }

    public boolean isUp() {
        return this.aUp;
    }

    public void setUp(boolean pUp) {
        this.aUp = pUp;
    }

    public boolean isRight() {
        return this.aRight;
    }

    public void setRight(boolean pRight) {
        this.aRight = pRight;
    }

    public boolean isDown() {
        return this.aDown;
    }

    public void setDown(boolean pDown) {
        this.aDown = pDown;
    }
}
