package entities;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstants.*;

import static utilz.HelpMethods.*;

public class Player extends Entity {
    private BufferedImage[][] aAnimations;
    private int aAnimTick;
    private int aAnimIndex;
    private final int aAnimSpeed = 25;
    private int aPlayerAction = IDLE;
//    private int aPlayerDirection = -1;
    private boolean aMoving = false, aAttacking = false;
    private float aPlayerSpeed = 1.0f * Game.SCALE; // Garder une vitesse constante en rapport avec la taille de la fenêtre

    private boolean aLeft, aUp, aRight, aDown, aJump;

    private int[][] aLvlData;

//    private float  aXDrawOffset = 40 * Game.SCALE;
//    private float aYDrawOffset = 38 * Game.SCALE;
    private float aXDrawOffset = 40 * Game.SCALE;
    private float aYDrawOffset = 38 * Game.SCALE;
    // 32 + 8 en x, 32 + 6 en y

    // JUMPING / GRAVITY
    private float aAirSpeed = 0f;
    private float aGravity = 0.04f * Game.SCALE;
    private float aJumpSpeed = -2.25f * Game.SCALE;
    private float aFallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean aInAir = false;

    public Player(final float pX, final float pY, final int pWidth, final int pHeight) {
        super(pX, pY, pWidth, pHeight);
        this.loadAnimations();

        this.initHitbox(pX + this.aXDrawOffset, pY + this.aYDrawOffset, (int)(16 * Game.SCALE), (int)(24 * Game.SCALE)); // ou height = 26 * scale.
        this.initSpriteBox(this.aX, this.aY, Game.PLAYER_SPRITE_SIZE * Game.SCALE, Game.PLAYER_SPRITE_SIZE * Game.SCALE);
//        this.initHitbox(pX, pY, 16 * Game.SCALE, 26 * Game.SCALE);


    }

    public void update() {
        this.updatePosition();
        this.updateAnimTick();
        this.setAnimation();
    }

    public void render(final Graphics pG) {
//        this.drawHitbox(pG);
//        this.drawSpriteBox(pG);

        pG.drawImage(this.aAnimations[this.aPlayerAction][this.aAnimIndex], (int)(this.aHitbox.x - this.aXDrawOffset), (int)(this.aHitbox.y - this.aYDrawOffset), this.aWidth, this.aHeight, null);
//        pG.drawImage(this.aAnimations[this.aPlayerAction][this.aAnimIndex], (int)(this.aHitbox.x), (int)(this.aHitbox.y), this.aWidth, this.aHeight, null);
    // TODO : actuellement : on affiche l'image en soustrayant les coordonnées d'offset de la sprite. A faire : afficher la sprite en elle meme comme avant en faisant l'offset sur la hitBox et non sur l'affichage. Faire drawSpriteBox()
    }

    /**
     * Charge les animations du Player dans un tableau 2D a partir de PLAYER_ATLAS
     */
    private void loadAnimations() {
        BufferedImage vImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        this.aAnimations = new BufferedImage[9][6];
        for(int vJ = 0; vJ < this.aAnimations.length; vJ++) {
            for (int vI = 0; vI < this.aAnimations[vJ].length; vI++) {
                this.aAnimations[vJ][vI] = vImg.getSubimage(vI * 96, (vJ + 8) * 96, 96, 96); // Commencer les animations a partir de l'épée
            }
        }
    }

    /**
     * Charge les informations de la map dans un tableau d'entier 2D
     * @param pLvlData Données de la map
     */
    public void loadLevelData(final int[][] pLvlData) {
        this.aLvlData = pLvlData;
        if(!IsEntityOnFloor(this.aHitbox, this.aLvlData)) {
            this.aInAir = true;
        }
    }

    /**
     * Change l'animation en fonction de l'état du Player, et reset les ticks si il y a changement
     */
    public void setAnimation() {
        int vStartAnim = this.aPlayerAction;
        if(this.aMoving) {
            this.aPlayerAction = RUNNING;
        } else {
            this.aPlayerAction = IDLE;
        }

        if(this.aInAir) {
            if(this.aAirSpeed < 0) {
                this.aPlayerAction = JUMP;
            } else {
                this.aPlayerAction = FALLING;
            }
        }

        if(this.aAttacking) {
            this.aPlayerAction = ATTACK;
        }

        if(this.aPlayerAction != vStartAnim) {
            this.resetAnimTick();
        }
    }

    /**
     * Reset les ticks d'animations
     */
    public void resetAnimTick() {
        this.aAnimTick = 0;
        this.aAnimIndex = 0;
    }

    /**
     * Modifie la position du Player ainsi que de sa Hitbox et de sa SpriteBox
     */
    public void updatePosition() {
        this.aMoving = false;

        if(this.aJump) {
            this.jump();
        }

        if(!this.aLeft && !this.aRight && !this.aInAir) {
            return;
        }
        float vXSpeed = 0;

        if(this.aLeft) {
            vXSpeed -= this.aPlayerSpeed;
        }
        if(this.aRight) {
            vXSpeed += this.aPlayerSpeed;
        }

        if(!this.aInAir) {
            if(!IsEntityOnFloor(this.aHitbox, this.aLvlData)) {
                this.aInAir = true;
            }
        }

        if(this.aInAir) {
            if(CanMoveHere(this.aHitbox.x, this.aHitbox.y + this.aAirSpeed, this.aHitbox.width, this.aHitbox.height, this.aLvlData)) {
                this.aHitbox.y += this.aAirSpeed;
                this.aAirSpeed += this.aGravity;
                this.updateXPos(vXSpeed);

                // Spritebox
                this.aSpriteBox.y += this.aAirSpeed;
            } else {
                this.aHitbox.y = GetEntityYPosUnderRoofAboveFloor(this.aHitbox, this.aAirSpeed);

                // SpriteBox
                this.aSpriteBox.y = GetEntityYPosUnderRoofAboveFloor(this.aHitbox, this.aAirSpeed) - this.aYDrawOffset;
                if(this.aAirSpeed > 0) {
                    this.resetInAir();
                } else {
                    this.aAirSpeed = this.aFallSpeedAfterCollision;
                }
                this.updateXPos(vXSpeed);
            }
        } else {
            this.updateXPos(vXSpeed);
        }
        this.aMoving = true;
    }

    /**
     * Modifie la position en X du Player, de sa Hitbox et de sa Spritebox
     * @param pXSpeed la vitesse
     */
    private void updateXPos(final float pXSpeed) {
        if(CanMoveHere(this.aHitbox.x + pXSpeed, this.aHitbox.y, this.aHitbox.width, this.aHitbox.height, this.aLvlData)) {
            this.aHitbox.x += pXSpeed;

            // Spritebox
            this.aSpriteBox.x += pXSpeed;
        } else {
            this.aHitbox.x = GetEntityXPosNextToWall(this.aHitbox, pXSpeed);
        }
    }

    private void jump() {
        if(this.aInAir) {
            return;
        }

        this.aInAir = true;
        this.aAirSpeed = this.aJumpSpeed;
    }

    private void resetInAir() {
        this.aInAir = false;
        this.aAirSpeed = 0;
    }

    /**
     * Détermine l'image a afficher pour jouer l'animation
     */
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

    public void setLeft(final boolean pLeft) {
        this.aLeft = pLeft;
    }

    public boolean isUp() {
        return this.aUp;
    }

    public void setUp(final boolean pUp) {
        this.aUp = pUp;
    }

    public boolean isRight() {
        return this.aRight;
    }

    public void setRight(final boolean pRight) {
        this.aRight = pRight;
    }

    public boolean isDown() {
        return this.aDown;
    }

    public void setDown(final boolean pDown) {
        this.aDown = pDown;
    }

    public boolean isJump() {
        return this.aJump;
    }

    public void setJump(final boolean pJump) {
        this.aJump = pJump;
    }
}
