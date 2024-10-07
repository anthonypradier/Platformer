package gamestates;

import main.Game;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    protected Game aGame;

    public State(final Game pGame) {
        this.aGame = pGame;
    }

    public boolean isIn(final MouseEvent e, final MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public Game getGame() {
        return this.aGame;
    }
}
