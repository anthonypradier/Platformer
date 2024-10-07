package inputs;

import entities.GameState;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel aGP;

    public MouseInputs(final GamePanel pGP) {
        this.aGP = pGP;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        switch (GameState.aState) {
            case PLAYING:
                this.aGP.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        switch (GameState.aState) {
            case MENU:
                this.aGP.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                this.aGP.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        switch (GameState.aState) {
            case MENU:
                this.aGP.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                this.aGP.getGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }

    @Override
    public void mouseDragged(final MouseEvent e) {

    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        switch (GameState.aState) {
            case MENU:
                this.aGP.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                this.aGP.getGame().getPlaying().mouseMoved(e);
                break;
            default:
                break;
        }
    }
}
