package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
                this.aGP.changeYDelta(-5);
                break;
            case KeyEvent.VK_Q:
                System.out.println("Left");
                this.aGP.changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
                System.out.println("Down");
                this.aGP.changeYDelta(5);
                break;
            case KeyEvent.VK_D:
                System.out.println("Right");
                this.aGP.changeXDelta(5);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
