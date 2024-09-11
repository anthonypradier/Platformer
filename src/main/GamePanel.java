package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {
    private MouseInputs aMouseInputs;
    private float aXDelta = 100, aYDelta = 100;
    private BufferedImage aImg;
    private BufferedImage[][] aAnimations;
    private int aAnimTick;
    private int aAnimIndex;
    private final int aAnimSpeed = 15;
    private int aPlayerAction = IDLE;
    private int aPlayerDirection = -1;
    private boolean aMoving = false;

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
        this.aAnimations = new BufferedImage[9][6];
        for(int vJ = 0; vJ < this.aAnimations.length; vJ++) {
            for (int vI = 0; vI < this.aAnimations[vJ].length; vI++) {
                this.aAnimations[vJ][vI] = this.aImg.getSubimage(vI * 96, (vJ + 8) * 96, 96, 96); // Commencer les animations a partir de l'épée
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

    public void setAnimation() {
        if(this.aMoving) {
            this.aPlayerAction = RUNNING;
        } else {
            this.aPlayerAction = IDLE;
        }
    }

    public void updatePosition() {
        if(this.aMoving) {
            switch (this.aPlayerDirection) {
                case LEFT:
                    this.aYDelta -= 5;
                // TODO : Faire les autres cas
            }
        }
    }

    public void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        this.updateAnimTick();
        this.setAnimation();

        pG.drawImage(this.aAnimations[this.aPlayerAction][this.aAnimIndex], (int)this.aXDelta, (int)this.aYDelta, 192, 192, null);
    }

    private void updateAnimTick() {
        this.aAnimTick++;
        if(this.aAnimTick == this.aAnimSpeed) {
            this.aAnimIndex++;
            this.aAnimTick = 0;

            if(this.aAnimIndex >= GetSpriteAmount(this.aPlayerAction)) {
                this.aAnimIndex = 0;
            }
        }
    }

    public void setDirection(final int pDirection) {
        this.aPlayerDirection = pDirection;
        this.aMoving = true;
    }

    public void setMoving(final boolean pMoving) {
        this.aMoving = pMoving;
    }
}
