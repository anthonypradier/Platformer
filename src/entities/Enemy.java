package entities;

import static utilz.Constants.EnemyConstants.*;

public abstract class Enemy extends Entity {
    private int aAnimIndex, aEnemyState, aEnemyType;
    private int aAnimTick, aAnimSpeed;

    public Enemy(final float pX, final float pY, final int pWidth, final int pHeight, final int pEnemyType) {
        super(pX, pY, pWidth, pHeight);
        this.aEnemyType = pEnemyType;
        this.aAnimSpeed = 25;
        this.initHitbox(pX, pY,  pWidth, pHeight);
    }

    public void updateAnimTick() {
        this.aAnimTick++;
        if(this.aAnimTick >= this.aAnimSpeed) {
            this.aAnimTick = 0;
            this.aAnimIndex++;
            if(this.aAnimIndex >= GetSpriteAmount(this.aEnemyType, this.aEnemyState)) {
                this.aAnimIndex = 0;
            }
        }
    }

    public void update() {
        this.updateAnimTick();
    }

    public int getAnimIndex() {
        return this.aAnimIndex;
    }

    public int getEnemyState() {
        return this.aEnemyState;
    }
}
