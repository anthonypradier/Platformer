package entities;

import java.awt.image.BufferedImage;

import static utilz.Constants.EnemyConstants.*;

public class Crabby extends Enemy {

    public Crabby(final float pX, final float pY) {
        super(pX, pY, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
    }
}
