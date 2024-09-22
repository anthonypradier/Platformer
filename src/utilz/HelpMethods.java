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
        if(!IsSolid(pX, pY, pLvlData)) {
            if(!IsSolid(pX + pWidth, pY + pHeight, pLvlData)) {
                if(!IsSolid(pX + pWidth, pY, pLvlData)) {
                    if(!IsSolid(pX, pY + pHeight, pLvlData)) {
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
    private static boolean IsSolid(final float pX, final float pY, int[][] pLvlData) {
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
     * @param pHitbox Sa Hitbox
     * @param pSpeed La vitesse de l'Entity
     * @return La position X de l'Entity
     */
    public static float GetEntityXPosNextToWall(final Rectangle2D.Float pHitbox, final float pSpeed) {
        int vCurrentTile = (int)(pHitbox.x / Game.TILE_SIZE);
        if(pSpeed > 0) {
            // Right, déplacement à droite
            int vTileXPos = vCurrentTile * Game.TILE_SIZE;
            int vXOffset = (int)(Game.TILE_SIZE - pHitbox.width);
            return vTileXPos + vXOffset - 1;
        } else {
            // Left, déplacement à gauche
            return vCurrentTile * Game.TILE_SIZE;
        }
    }

    public static float GetEntityYPosUnderRoofAboveFloor(final Rectangle2D.Float pHitbox, final float pAirSpeed) {
        int vCurrentTile = (int)(pHitbox.y / Game.TILE_SIZE);

        if(pAirSpeed > 0) {
            // Falling - touching floor
            int vTileYPos = vCurrentTile * Game.TILE_SIZE;
            int vYOffset = (int)(Game.TILE_SIZE - pHitbox.height);
            return vTileYPos + vYOffset - 1;
        } else {
            // Jumping
            return vCurrentTile * Game.TILE_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(final Rectangle2D.Float pHitbox, final int[][] pLvlData) {
        // Check the pixel below bottomleft and bottomright
        if(!IsSolid(pHitbox.x, pHitbox.y + pHitbox.height + 1, pLvlData) && !IsSolid(pHitbox.x + pHitbox.width, pHitbox.y + pHitbox.height + 1, pLvlData)) {

            System.out.println(IsSolid(pHitbox.x, pHitbox.y + pHitbox.height + 1, pLvlData) + " " + IsSolid(pHitbox.x + pHitbox.width, pHitbox.y + pHitbox.height + 1, pLvlData));
            return false;
        }
        return true;
    }
}
