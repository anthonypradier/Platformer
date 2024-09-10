package main;

import javax.swing.*;

public class GameWindow {
    private JFrame aJFrame;
    public GameWindow(final GamePanel pGP) {
        this.aJFrame = new JFrame();
        this.aJFrame.setSize(400, 400);
        this.aJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.aJFrame.setLocationRelativeTo(null);
        this.aJFrame.add(pGP);
        this.aJFrame.setVisible(true);
    }
}
