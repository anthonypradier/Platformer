package main;

import javax.swing.*;

public class GameWindow {
    private JFrame aJFrame;
    public GameWindow(final GamePanel pGP) {
        this.aJFrame = new JFrame();
        this.aJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.aJFrame.add(pGP);
        this.aJFrame.setResizable(false);
        this.aJFrame.pack();
        this.aJFrame.setLocationRelativeTo(null);
        this.aJFrame.setVisible(true);
    }
}
