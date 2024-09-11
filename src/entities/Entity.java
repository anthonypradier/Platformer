package entities;

public abstract class Entity {
    protected float aX, aY;
    public Entity(final float pX, final float pY) {
        this.aX = pX;
        this.aY = pY;
    }

    public void update() {}
    public void render() {}
}
