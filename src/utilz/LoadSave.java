package utilz;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    // Nom de fichiers
    public static final String PLAYER_ATLAS = "/Player-sprites-export.png";
    public static final String LEVEL_ATLAS = "/Outside-sprites.png";
    public static final String LEVEL_ONE_DATA = "/Level-one-data-export.png";

    /**
     * Charge et renvoie l'image liée au nom de fichier
     * @param pFileName nom de fichier
     * @return L'image
     */
    public static BufferedImage GetSpriteAtlas(final String pFileName) {
        BufferedImage vImg = null;
        InputStream vIS = LoadSave.class.getResourceAsStream(pFileName);
        try {
            vImg = ImageIO.read(vIS);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                vIS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return vImg;
    }

    public static int[][] GetLevelData() {
        int[][] vLevelData = new int[Game.TILE_IN_HEIGHT][Game.TILE_IN_WIDTH];
        BufferedImage vImg = GetSpriteAtlas(LEVEL_ONE_DATA);

        for(int vJ = 0; vJ < vImg.getHeight(); vJ++) {
            for(int vI = 0; vI < vImg.getWidth(); vI++) {
                Color vColor = new Color(vImg.getRGB(vI, vJ));
                int vValue = vColor.getRed();
                if(vValue >= 48) {
                    vValue = 11;
                }
                vLevelData[vJ][vI] = vValue;
            }
        }
        return vLevelData;
    }
}
