package entities;

import main.Game;
import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.Directions.*;
import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.PlayerConstants.*;

import static utilz.HelpMethods.CanMoveHere;

public class Player extends Entity {
    private BufferedImage[][] aAnimations;
    private int aAnimTick;
    private int aAnimIndex;
    private final int aAnimSpeed = 25;
    private int aPlayerAction = IDLE;
    private int aPlayerDirection = -1;
    private boolean aMoving = false, aAttacking = false;
    private float aPlayerSpeed = 2.0f;

    private boolean aLeft, aUp, aRight, aDown;

    private int[][] aLvlData;

//    private float  aXDrawOffset = 40 * Game.SCALE;
//    private float aYDrawOffset = 38 * Game.SCALE;
    private float aXDrawOffset = 40 * Game.SCALE;
    private float aYDrawOffset = 38 * Game.SCALE;
    // 32 + 8 en x, 32 + 6 en y

    public Player(final float pX, final float pY, final int pWidth, final int pHeight) {
        super(pX, pY, pWidth, pHeight);
        this.loadAnimations();

        this.aXDrawOffset = 40 * Game.SCALE;
        this.aYDrawOffset = 38 * Game.SCALE;

        this.initHitbox(pX + this.aXDrawOffset, pY + this.aYDrawOffset, 16 * Game.SCALE, 26 * Game.SCALE);
        this.initHitbox(pX, pY, 16 * Game.SCALE, 26 * Game.SCALE);
    }

    public void update() {
        this.updatePosition();
        this.updateAnimTick();
        this.setAnimation();
    }

    public void render(final Graphics pG) {
        this.drawHitbox(pG);

        this.drawSpriteBox(pG);

        pG.drawImage(this.aAnimations[this.aPlayerAction][this.aAnimIndex], (int)(this.aHitbox.x - this.aXDrawOffset), (int)(this.aHitbox.y - this.aYDrawOffset), this.aWidth, this.aHeight, null);
//        pG.drawImage(this.aAnimations[this.aPlayerAction][this.aAnimIndex], (int)(this.aHitbox.x), (int)(this.aHitbox.y), this.aWidth, this.aHeight, null);
        this.drawSpriteBox(pG);
//        pG.drawImage(this.aAnimations[this.aPlayerAction][this.aAnimIndex], (int)(this.aHitbox.x - this.aXDrawOffset), (int)(this.aHitbox.y - this.aYDrawOffset), this.aWidth, this.aHeight, null);
        pG.drawImage(this.aAnimations[this.aPlayerAction][this.aAnimIndex], (int)(this.aHitbox.x - this.aXDrawOffset), (int)(this.aHitbox.y - this.aYDrawOffset), this.aWidth, this.aHeight, null);
    // TODO : actuellement : on affiche l'image en soustrayant les coordonnées d'offset de la sprite. A faire : afficher la sprite en elle meme comme avant en faisant l'offset sur la hitBox et non sur l'affichage. Faire drawSpriteBox()
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

    public void loadLevelData(final int[][] pLvlData) {
        this.aLvlData = pLvlData;
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
        if(!this.aLeft && !this.aUp && !this.aRight && !this.aDown) {
            return;
        }
        float vXSpeed = 0; float vYSpeed = 0;

        if(this.aLeft && !this.aRight) {
            vXSpeed = -this.aPlayerSpeed;
        } else if(!this.aLeft  && this.aRight) {
            vXSpeed = this.aPlayerSpeed;
        }

        if(this.aUp && !this.aDown) {
            vYSpeed = -this.aPlayerSpeed;
        } else if(!this.aUp  && this.aDown) {
            vYSpeed = this.aPlayerSpeed;
        }

        if(CanMoveHere(this.aHitbox.x + vXSpeed, this.aHitbox.y + vYSpeed, this.aHitbox.width, this.aHitbox.height, this.aLvlData)) {
            this.aHitbox.x += vXSpeed;
            this.aHitbox.y += vYSpeed;

            this.aSpriteBox.x += vXSpeed;
            this.aSpriteBox.y += vYSpeed;
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
