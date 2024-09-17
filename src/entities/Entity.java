package entities;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float aX, aY;
    protected float aXDrawOffset, aYDrawOffset;
    protected int aWidth, aHeight;
    protected Rectangle2D.Float aSpriteBox;
    protected Rectangle2D.Float aHitbox;

    public Entity(final float pX, final float pY, final int pWidth, final int pHeight) {
        this.aX = pX;
        this.aY = pY;
        this.aWidth = pWidth;
        this.aHeight = pHeight;

        this.initSpriteBox();
    }

    protected void drawHitbox(final Graphics pG) {
        // For debugging hitbox
        pG.setColor(Color.pink);
        pG.drawRect((int)(this.aHitbox.x), (int)(this.aHitbox.y), (int)this.aHitbox.width, (int)this.aHitbox.height);
    }

    protected void drawSpriteBox(final Graphics pG) {
        pG.setColor(Color.RED);
        pG.drawRect((int)this.aSpriteBox.x, (int)this.aSpriteBox.y, (int)(Game.PLAYER_SPRITE_SIZE * Game.SCALE), (int)(Game.PLAYER_SPRITE_SIZE * Game.SCALE));
    }

    protected void initHitbox(final float pX, final float pY, final float pWidth, final float pHeight) {
        this.aHitbox = new Rectangle2D.Float(pX, pY, pWidth, pHeight);
    }

    protected void initSpriteBox() {
        this.aSpriteBox = new Rectangle2D.Float(this.aX, this.aY, Game.PLAYER_SPRITE_SIZE * Game.SCALE, Game.PLAYER_SPRITE_SIZE * Game.SCALE);
    }

    /*protected void updateHitbox() {
        this.aHitbox.x = (int)this.aX;
        this.aHitbox.y = (int)this.aY;

    }*/

    public Rectangle2D.Float getHitbox() {
        return this.aHitbox;
    }

    public Rectangle2D.Float getSpriteBox() {
        return this.aSpriteBox;
    }

    public void update() {}
    public void render() {}
}
