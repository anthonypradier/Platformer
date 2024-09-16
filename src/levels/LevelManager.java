package levels;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;


public class LevelManager {
    private Game aGame;
    private BufferedImage[] aLevelSprite;
    private Level aLevel1;

    public LevelManager(final Game pGame) {
        this.aGame = pGame;
        this.importOutsideSprites();
        this.aLevel1 = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage vImg = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        this.aLevelSprite = new BufferedImage[48];
        for(int vJ = 0; vJ < 4; vJ++) {
            for(int vI = 0; vI < 12; vI++) {
                int vIndex = vJ * 12 + vI;
                this.aLevelSprite[vIndex] = vImg.getSubimage(vI * 32, vJ * 32, 32, 32);
            }
        }
    }

    public void draw(final Graphics pG) {
        for(int vJ = 0; vJ < Game.TILE_IN_HEIGHT; vJ++) {
            for(int vI = 0; vI < Game.TILE_IN_WIDTH; vI++) {
                int vIndex = this.aLevel1.getSpriteIndex(vI, vJ);
                if(vIndex == 255) {
                    continue;
                } else {
                    pG.drawImage(this.aLevelSprite[vIndex], vI * Game.TILE_SIZE, vJ * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, null);
                }
            }
        }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return this.aLevel1;
    }
}
