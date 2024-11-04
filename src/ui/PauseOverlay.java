package ui;

import entities.GameState;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE;
import static utilz.Constants.UI.URMButtons.URM_SIZE;
import static utilz.Constants.UI.VolumeButtons.*;

public class PauseOverlay {
    private Playing aPlaying;
    private BufferedImage aBackgroundImg;
    private SoundButton aMusicButton, aSFXButton;
    private UrmButton aUnpauseButton, aReplayButton, aMenuButton;
    private VolumeButton aVolumeButton;
    private int aBgX, aBgY, aBgW, aBgH;

    public PauseOverlay(final Playing pPlaying) {
        this.aPlaying = pPlaying;

        this.loadBackground();
        this.createSoundButtons();
        this.createURMButtons();
        this.createVolumeButtons();
    }

    private void createVolumeButtons() {
        int vX = (int)(309 * Game.SCALE);
        int vY = (int)(278 * Game.SCALE);
        this.aVolumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void createURMButtons() {
        int vY = (int)(325 * Game.SCALE);
        int vUnpauseX = (int)(313 * Game.SCALE);
        int vReplayX = (int)(387 * Game.SCALE);
        int vMenuX = (int)(462 * Game.SCALE);

        //TODO : Change the rowIndex parameter to a constant inside the constant class
        this.aMenuButton = new UrmButton(vMenuX, vY, URM_SIZE, URM_SIZE, 2);
        this.aUnpauseButton = new UrmButton(vUnpauseX, vY, URM_SIZE, URM_SIZE, 0);
        this.aReplayButton = new UrmButton(vReplayX, vY, URM_SIZE, URM_SIZE, 1);
    }

    private void createSoundButtons() {
        int vSoundX = (int)(450 * Game.SCALE);
        int vMusicY = (int)(135 * Game.SCALE);
        int vSFXY = (int)(180 * Game.SCALE);

        this.aMusicButton = new SoundButton(vSoundX, vMusicY, SOUND_SIZE, SOUND_SIZE);
        this.aSFXButton = new SoundButton(vSoundX, vSFXY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        this.aBackgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        this.aBgW = (int)(this.aBackgroundImg.getWidth() * Game.SCALE);
        this.aBgH = (int)(this.aBackgroundImg.getHeight() * Game.SCALE);
        this.aBgX = (int)(Game.GAME_WIDTH / 2 - this.aBgW / 2);
        this.aBgY = (int)(20 * Game.SCALE);
    }

    public void update() {
        this.aMusicButton.update();
        this.aSFXButton.update();

        this.aUnpauseButton.update();
        this.aMenuButton.update();
        this.aReplayButton.update();

        this.aVolumeButton.update();
    }

    public void draw(final Graphics pG) {

        // Background
        pG.drawImage(this.aBackgroundImg, this.aBgX, this.aBgY, this.aBgW, this.aBgH, null);

        // Buttons
        this.aMusicButton.draw(pG);
        this.aSFXButton.draw(pG);

        this.aUnpauseButton.draw(pG);
        this.aMenuButton.draw(pG);
        this.aReplayButton.draw(pG);

        this.aVolumeButton.draw(pG);
    }

    public void mouseClicked(final MouseEvent e) {

    }

    public void mousePressed(final MouseEvent e) {
        // Sound buttons
        if(this.isIn(e, this.aMusicButton)) {
            this.aMusicButton.setMousePressed(true);
        } else if(this.isIn(e, this.aSFXButton)) {
            this.aSFXButton.setMousePressed(true);
        } else if(this.isIn(e, this.aMenuButton)) {
            this.aMenuButton.setMousePressed(true);
        } else if(this.isIn(e, this.aReplayButton)) {
            this.aReplayButton.setMousePressed(true);
        } else if(this.isIn(e, this.aUnpauseButton)) {
            this.aUnpauseButton.setMousePressed(true);
        } else if(this.isIn(e, this.aVolumeButton)) {
            this.aVolumeButton.setMousePressed(true);
        }
    }

    public void mouseReleased(final MouseEvent e) {
        // Sound Buttons
        if(this.isIn(e, this.aMusicButton)) {
            if(this.aMusicButton.isMousePressed()) {
                this.aMusicButton.setMuted(!this.aMusicButton.isMuted());
            }
        } else if(this.isIn(e, this.aSFXButton)) {
            if(this.aSFXButton.isMousePressed()) {
                this.aSFXButton.setMuted(!this.aSFXButton.isMuted());
            }
        } else if(this.isIn(e, this.aUnpauseButton)) {
            if(this.aUnpauseButton.isMousePressed()) {
                this.aPlaying.unpauseGame();
            }
        } else if(this.isIn(e, this.aReplayButton)) {
            if(this.aReplayButton.isMousePressed()) {
                System.out.println("Replay !");
            }
        } else if(this.isIn(e, this.aMenuButton)) {
            if(this.aMenuButton.isMousePressed()) {
                GameState.aState = GameState.MENU;
                this.aPlaying.unpauseGame();
            }
        }

        // unpress the button and un-over the button if we release the cursor outside the button we pressed
        this.aMusicButton.resetBools();
        this.aSFXButton.resetBools();

        this.aUnpauseButton.resetBools();
        this.aReplayButton.resetBools();
        this.aMenuButton.resetBools();

        this.aVolumeButton.resetBools();
    }

    public void mouseMoved(final MouseEvent e) {
        // Sound buttons
        this.aMusicButton.setMouseOver(false);
        this.aSFXButton.setMouseOver(false);
        // Urm buttons
        this.aMenuButton.setMouseOver(false);
        this.aReplayButton.setMouseOver(false);
        this.aUnpauseButton.setMouseOver(false);
        // Volume button
        this.aVolumeButton.setMouseOver(false);

        if(this.isIn(e, this.aMusicButton)) {
            this.aMusicButton.setMouseOver(true);
        } else if(this.isIn(e, this.aSFXButton)) {
            this.aSFXButton.setMouseOver(true);
        } else if(this.isIn(e, this.aUnpauseButton)) {
            this.aUnpauseButton.setMouseOver(true);
        } else if(this.isIn(e, this.aReplayButton)) {
            this.aReplayButton.setMouseOver(true);
        } else if(this.isIn(e, this.aMenuButton)) {
            this.aMenuButton.setMouseOver(true);
        } else if(this.isIn(e, this.aVolumeButton)) {
            this.aVolumeButton.setMouseOver(true);
        }

    }

    public void mouseDragged(final MouseEvent e) {
        if(this.aVolumeButton.isMousePressed()) {
            this.aVolumeButton.changeX(e.getX());
        }
    }

    private boolean isIn(final MouseEvent e, final PauseButton pButton) {
        return (pButton.getBounds().contains(e.getX(), e.getY()));
    }
}
