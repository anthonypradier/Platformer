package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

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
        Dimension vSize = new Dimension(1280, 800);
        this.setMinimumSize(vSize);
        this.setPreferredSize(vSize);
        this.setMaximumSize(vSize);
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
