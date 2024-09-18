package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {
    /**
     * Détermine si l'Entity peut se déplacer à sa coordonnée suivante
     * @param pX prochain X
     * @param pY prochain Y
     * @param pWidth largeur
     * @param pHeight hauteur
     * @param pLvlData level data
     * @return si l'Entity peut se déplacer
     */
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

    /**
     * Détermine si la tuile avec laquelle l'Entity entre en collision est solide ou pas
     * @param pX prochain X
     * @param pY prochain Y
     * @param pLvlData
     * @return Si la tuile est solide
     */
    private static boolean isSolid(final float pX, final float pY, int[][] pLvlData) {
        if(pX < 0 || pX >= Game.GAME_WIDTH) {
            return true;
        }
        if(pY < 0 || pY >= Game.GAME_HEIGHT) {
            return true;
        }
        // Détermine la tuile pour laquelle on veut connaitre la solidité
        float vXIndex = pX / Game.TILE_SIZE;
        float vYIndex = pY / Game.TILE_SIZE;

        int vValue = pLvlData[(int)vYIndex][(int)vXIndex];
        if(vValue < 0 || vValue >= 48 || vValue != 11) {
            return true;
        }
        return false;
    }

    /**
     * Détermine la position de l'Entity lorsqu'il entre en collision avec un mur
     * @param pHitBox Sa Hitbox
     * @param pSpeed La vitesse de l'Entity
     * @return La position X de l'Entity
     */
    public static float GetEntityXPosNextToWall(final Rectangle2D.Float pHitBox, final float pSpeed) {
        int vCurrentTile = (int)(pHitBox.x / Game.TILE_SIZE);
        if(pSpeed > 0) {
            // Right, déplacement à droite
            int vTileXPos = vCurrentTile * Game.TILE_SIZE;
            int vXOffset = (int)(Game.TILE_SIZE - pHitBox.width);
            return vTileXPos + vXOffset - 1;
        } else {
            // Left, déplacement à gauche
            return vCurrentTile * Game.TILE_SIZE;
        }
    }
}
