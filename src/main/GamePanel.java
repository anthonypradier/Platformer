package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public GamePanel() {

    }

    public void paintComponent(final Graphics pG) {
        super.paintComponent(pG);
        pG.setColor(Color.pink);
        pG.fillRect(50, 50, 100, 100);
    }
}
