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

    public void setPosition(Point point) {
        setPosition(point.x, point.y);
    }

    public void setNullPosition() {
        relativePosition = null;
        absolutePosition = null;
    }

    public Point getRelativePosition() {
        if (relativePosition != null) {
            return new Point(relativePosition);
        } else {
            return null;
        }
    }

    public void drawSprite(Graphics g) {
        if (relativePosition != null) {
            img.paintIcon(null, g, absolutePosition.x, absolutePosition.y);
        }
    }

    public abstract String overlap(int x, int y);
}
