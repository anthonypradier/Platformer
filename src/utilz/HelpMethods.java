package utilz;

import main.Game;

public class HelpMethods {
    public static boolean CanMoveHere(final float pX, final float pY, final float pWidth, final float pHeight, int[][] pLvlData) {
        if(!isSolid(pX, pY, pLvlData)) {
            if(!isSolid(pX + pWidth, pY + pHeight, pLvlData)) {
                if(!isSolid(pX + pWidth, pY, pLvlData)) {
                    if(!isSolid(pX, pY + pHeight, pLvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSolid(final float pX, final float pY, int[][] pLvlData) {
        if(pX < 0 || pX >= Game.GAME_WIDTH) {
            return true;
        }
        if(pY < 0 || pY >= Game.GAME_HEIGHT) {
            return true;
        }
        float vXIndex = pX / Game.TILE_SIZE;
        float vYIndex = pY / Game.TILE_SIZE;

        int vValue = pLvlData[(int)vYIndex][(int)vXIndex];
        if(vValue < 0 || vValue >= 48 || vValue != 11) {
            return true;
        }
        return false;
    }
}
