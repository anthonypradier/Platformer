package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

    public void update();
    public void draw(Graphics pG);
    public void mouseClicked(final MouseEvent e);
    public void mousePressed(final MouseEvent e);
    public void mouseReleased(final MouseEvent e);
    public void mouseMoved(final MouseEvent e);
    public void keyPressed(final KeyEvent e);
    public void keyReleased(final KeyEvent e);

}
