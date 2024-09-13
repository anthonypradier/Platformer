package levels;

public class Level {
    private int[][] aLevelData;

    public Level(final int[][] pLvlData) {
        this.aLevelData = pLvlData;
    }

    public int getSpriteIndex(final int pX, final int pY) {
        return this.aLevelData[pY][pX];
    }

    public int[][] getLevel() {
        return this.aLevelData;
    }
}
