package entities;

import gamestates.Playing;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
    private Playing aPlaying;
    private BufferedImage[][] aCrabbyArray;
    private ArrayList<Crabby> aCrabbies = new ArrayList<Crabby>();

    public EnemyManager(final Playing pPlaying) {
        this.aPlaying = pPlaying;
        this.loadImages();
        this.addEnemies();
    }

    public void update() {
        for(Crabby vCrabby : this.aCrabbies) {
            vCrabby.update();
        }
    }

    public void draw(final Graphics pG, final int pXLvlOffset) {
        this.drawCrabs(pG, pXLvlOffset);
    }

    public void drawCrabs(final Graphics pG, final int pXLvlOffset) {
        for(Crabby vCrabby : this.aCrabbies) {
            pG.drawImage(this.aCrabbyArray[vCrabby.getEnemyState()][vCrabby.getAnimIndex()], (int)vCrabby.getHitbox().x - pXLvlOffset, (int)vCrabby.getHitbox().y, CRABBY_WIDTH, CRABBY_HEIGHT, null);
        }
    }

    public void addEnemies() {
        this.aCrabbies = LoadSave.GetCrabs();
        System.out.println("size of crabs " + this.aCrabbies.size());
    }

    private void loadImages() {
        this.aCrabbyArray = new BufferedImage[5][9];
        BufferedImage vTemp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITES);
//        System.out.println("Width : " + vTemp.getWidth() + " | Height : " + vTemp.getHeight());
        for(int i = 0; i < this.aCrabbyArray.length; i++) {
            for(int j = 0; j < this.aCrabbyArray[i].length; j++) {
//                System.out.println(j * CRABBY_WIDTH_DEFAULT + "   " + );
                this.aCrabbyArray[i][j] = vTemp.getSubimage(j * CRABBY_WIDTH_DEFAULT, i * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
            }
        }
    }
}
