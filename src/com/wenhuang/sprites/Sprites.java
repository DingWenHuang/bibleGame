package com.wenhuang.sprites;

import com.wenhuang.Main;

import javax.swing.*;
import java.awt.*;

public abstract class Sprites {

    protected Point relativePosition;
    protected Point absolutePosition;
    protected ImageIcon img;

    public void setPosition(int x, int y) {
        relativePosition = new Point(x, y);
        absolutePosition = new Point((x - 1) * Main.CELL_SIZE, (y - 1) * Main.CELL_SIZE);
    }

    public void drawSprite(Graphics g) {
        img.paintIcon(null, g, absolutePosition.x, absolutePosition.y);
    }

    public abstract String overlap(int x, int y);
}
