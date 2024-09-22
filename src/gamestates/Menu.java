package gamestates;

import entities.GameState;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements StateMethods{

    public Menu(final Game pGame) {
        super(pGame);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(final Graphics pG) {
        pG.setColor(Color.BLACK);
        pG.drawString("MENU", Game.GAME_WIDTH / 2, 200);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {

    }

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseMoved(final MouseEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            GameState.aState = GameState.PLAYING;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {

    }
}
