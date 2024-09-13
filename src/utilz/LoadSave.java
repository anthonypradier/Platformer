package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "/Player-Sheet.png";

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
}
