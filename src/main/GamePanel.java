package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {
    private Game aGame;
    private MouseInputs aMouseInputs;

    public GamePanel(final Game pGame) {
        this.aGame = pGame;
        this.aMouseInputs = new MouseInputs(this);

        this.setPanelSize();

        this.addKeyListener(new KeyboardInputs(this));
        this.addMouseListener(this.aMouseInputs);
        this.addMouseMotionListener(this.aMouseInputs);
    }


    private void setPanelSize() {
        Dimension vSize = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        this.setPreferredSize(vSize);
        System.out.println("size : " + GAME_WIDTH + " | " + GAME_HEIGHT);
    }

    public void updateGame() {
    }

    public void paintComponent(final Graphics pG) {
        super.paintComponent(pG);
        this.aGame.render(pG);
    }

    public Game getGame() {
        return this.aGame;
    }
}
