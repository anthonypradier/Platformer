package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInputs aMouseInputs;
    private float aXDelta = 100, aYDelta = 100;
    private float aXDir = 1f, aYDir = 1f;
    private int aFrame = 0;
    private long aLastCheck = 0;
    private Color aColor;
    public GamePanel() {
        this.aMouseInputs = new MouseInputs(this);

        this.addKeyListener(new KeyboardInputs(this));
        this.addMouseListener(this.aMouseInputs);
        this.addMouseMotionListener(this.aMouseInputs);
    }

    public void paintComponent(final Graphics pG) {
        super.paintComponent(pG);
        pG.setColor(this.aColor);
        pG.fillRect((int)this.aXDelta, (int)this.aYDelta, 200, 50);
        this.updateRectangle();
    }

    public void updateRectangle() {
        this.aXDelta += this.aXDir;
        if(this.aXDelta > 400 || this.aXDelta < 0) {
            this.aXDir *= -1;
            this.aColor = this.getRandomColor();
        }
        this.aYDelta += this.aYDir;
        if(this.aYDelta > 400 || this.aYDelta < 0) {
            this.aYDir *= -1;
            this.aColor = this.getRandomColor();
        }
    }

    public Color getRandomColor() {
        Random vRandom = new Random();
        int vR = vRandom.nextInt(256);
        int vG = vRandom.nextInt(256);
        int vB = vRandom.nextInt(256);
        return new Color(vR, vG, vB);
    }

    public void changeXDelta(final int pN) {
        this.aXDelta += pN;
        this.repaint();
    }

    public void changeYDelta(final int pN) {
        this.aYDelta += pN;
        this.repaint();
    }
}
