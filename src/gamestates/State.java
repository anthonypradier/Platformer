package gamestates;

import main.Game;

public class State {
    protected Game aGame;

    public State(final Game pGame) {
        this.aGame = pGame;
    }

    public Game getGame() {
        return this.aGame;
    }
}
