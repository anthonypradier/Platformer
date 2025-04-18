package inputs;

import entities.GameState;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private final GamePanel aGP;

    public MouseInputs(final GamePanel pGP) {
        this.aGP = pGP;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            switch (GameState.aState) {
                case PLAYING:
                    this.aGP.getGame().getPlaying().mouseClicked(e);
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            switch (GameState.aState) {
                case PLAYING:
                    this.aGP.getGame().getPlaying().mousePressed(e);
                    break;
                case MENU:
                    this.aGP.getGame().getMenu().mousePressed(e);
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            switch (GameState.aState) {
                case PLAYING:
                    this.aGP.getGame().getPlaying().mouseReleased(e);
                    break;
                case MENU:
                    this.aGP.getGame().getMenu().mouseReleased(e);
                    break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameState.aState) {
            case MENU:
                break;
            case PLAYING:
                this.aGP.getGame().getPlaying().mouseDragged(e);
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
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
