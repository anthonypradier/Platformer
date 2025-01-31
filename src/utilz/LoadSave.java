package utilz;

import entities.Crabby;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.CRABBY;

public class LoadSave {
    // Nom de fichiers
    public static final String PLAYER_ATLAS = "/Player_sprites.png";
    public static final String LEVEL_ATLAS = "/Outside_sprites.png";
//    public static final String LEVEL_ONE_DATA = "/Level_one_data.png";
    public static final String LEVEL_ONE_DATA = "/Level_one_data_long.png";
    public static final String MENU_BUTTONS = "/Button_atlas.png";
    public static final String MENU_BACKGROUND = "/Menu_background.png";
    public static final String MENU_BACKGROUND_IMG = "/Background_menu.png";
    public static final String PAUSE_BACKGROUND = "/Pause_menu.png";
    public static final String SOUND_BUTTONS = "/Sound_button.png";
    public static final String URM_BUTTONS = "/Urm_buttons.png";
    public static final String VOLUME_BUTTONS = "/Volume_buttons.png";
    public static final String PLAYING_BG_IMG = "/playing_bg_img.png";
    public static final String BIG_CLOUDS = "/big_clouds.png";
    public static final String SMALL_CLOUDS = "/small_clouds.png";
    public static final String CRABBY_SPRITES = "/crabby_sprite.png";

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

    public static ArrayList<Crabby> GetCrabs() {
        BufferedImage vImg = GetSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Crabby> vList = new ArrayList<>();

        for(int vJ = 0; vJ < vImg.getHeight(); vJ++) {
            for(int vI = 0; vI < vImg.getWidth(); vI++) {
                Color vColor = new Color(vImg.getRGB(vI, vJ));
                int vValue = vColor.getGreen();
                if(vValue == CRABBY) {
                    vList.add(new Crabby(vI * Game.TILE_SIZE, vJ * Game.TILE_SIZE));
                }
            }
        }
        return vList;
    }

    public static int[][] GetLevelData() {
        BufferedImage vImg = GetSpriteAtlas(LEVEL_ONE_DATA);
        int[][] vLevelData = new int[vImg.getHeight()][vImg.getWidth()];

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
