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

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                System.out.println("Up");
                this.aGP.setDirection(UP);
                break;
            case KeyEvent.VK_Q:
                System.out.println("Left");
                this.aGP.setDirection(LEFT);
                break;
            case KeyEvent.VK_S:
                System.out.println("Down");
                this.aGP.setDirection(DOWN);
                break;
            case KeyEvent.VK_D:
                System.out.println("Right");
                this.aGP.setDirection(RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
            case KeyEvent.VK_Q:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                this.aGP.setMoving(false);
        }
    }
}
