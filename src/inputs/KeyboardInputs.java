package inputs;

import entities.GameState;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private final GamePanel aGP;
     public KeyboardInputs(final GamePanel pGP) {
         this.aGP = pGP;
     }
    @Override
    public void keyTyped(final KeyEvent e) {

    }
    // TODO : Créer une classe par touches d'après RhumOne. HashMap + liste des touches appuyées simultanément => plus facile a maintenir et lisible
    @Override
    public void keyPressed(final KeyEvent e) {
        switch (GameState.aState) {
            case MENU:
                this.aGP.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                this.aGP.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        switch (GameState.aState) {
            case MENU:
                this.aGP.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                this.aGP.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
    }
}
