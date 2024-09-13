package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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
        this.aJFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                pGP.getGame().windowFocusLost();
            }
        });
    }
}
