package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private MouseInputs aMouseInputs;
    private float aXDelta = 100, aYDelta = 100;
    private BufferedImage aImg;
    private BufferedImage[][] aAnimations;
    private int aAnimTick;
    private int aAnimIndex;
    private final int aAnimSpeed = 15;

    public GamePanel() {
        this.aMouseInputs = new MouseInputs(this);

        this.importImg();
        this.loadAnimations();
        this.setPanelSize();

        this.addKeyListener(new KeyboardInputs(this));
        this.addMouseListener(this.aMouseInputs);
        this.addMouseMotionListener(this.aMouseInputs);
    }

    private void loadAnimations() {
        this.aAnimations = new BufferedImage[19][6];
        for(int vJ = 0; vJ < this.aAnimations.length; vJ++) {
            for (int vI = 0; vI < this.aAnimations[vJ].length; vI++) {
                this.aAnimations[vJ][vI] = this.aImg.getSubimage(vI * 96, vJ * 96, 96, 96);
            }
        }
    }

    private void importImg() {
        InputStream vIS = this.getClass().getResourceAsStream("/Player-Sheet.png");
        try {
            this.aImg = ImageIO.read(vIS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension vSize = new Dimension(1280, 800);
        this.setMinimumSize(vSize);
        this.setPreferredSize(vSize);
        this.setMaximumSize(vSize);
    }

    public void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        this.updateAnimTick();
        pG.drawImage(this.aAnimations[2][1], (int)this.aXDelta, (int)this.aYDelta, 192, 192, null);
    }

    private void updateAnimTick() {
//        this.aAnimTick++;
//        if(this.aAnimTick == this.aAnimSpeed) {
//            this.aAnimIndex++;
//            this.aAnimTick = 0;
//
//            if(this.aAnimIndex == this.aRunAnim.length) {
//                this.aAnimIndex = 0;
//            }
//        }
    }

    public void changeXDelta(final int pN) {
        this.aXDelta += pN;
        this.repaint();
    }

    public void changeYDelta(final int pN) {
        this.aYDelta += pN;
        this.repaint();
    }

    public void setRectPosition(final int pX, final int pY) {
        this.aXDelta = pX;
        this.aYDelta = pY;
    }
}
