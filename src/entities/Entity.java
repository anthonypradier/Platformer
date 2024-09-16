package entities;

import java.awt.*;

public abstract class Entity {
    protected float aX, aY;
    protected int aWidth, aHeight;
    protected Rectangle aHitbox;

    public Entity(final float pX, final float pY, final int pWidth, final int pHeight) {
        this.aX = pX;
        this.aY = pY;
        this.aWidth = pWidth;
        this.aHeight = pHeight;

        this.initHitbox();
    }

    protected void drawHitbox(final Graphics pG) {
        // For debugging hitbox
        pG.setColor(Color.pink);
        pG.drawRect(this.aHitbox.x, this.aHitbox.y, this.aHitbox.width, this.aHitbox.height);
    }

    private void initHitbox() {
        this.aHitbox = new Rectangle((int)this.aX, (int)this.aY, this.aWidth, this.aHeight);
    }

    protected void updateHitbox() {
        this.aHitbox.x = (int)this.aX;
        this.aHitbox.y = (int)this.aY;

    }

    public Rectangle getHitbox() {
        return this.aHitbox;
    }

    public void update() {}
    public void render() {}
}
