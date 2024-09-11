package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilz.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {
    private GamePanel aGP;
     public KeyboardInputs(final GamePanel pGP) {
         this.aGP = pGP;
     }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    // TODO : Créer une classe par touches d'après RhumOne. HashMap + liste des touches appuyées simultanément => plus facile a maintenir et lisible
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                this.aGP.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_Q:
                this.aGP.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                this.aGP.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                this.aGP.getGame().getPlayer().setRight(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                this.aGP.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_Q:
                this.aGP.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                this.aGP.getGame().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
                this.aGP.getGame().getPlayer().setRight(false);
                break;
        }
    }
}
