package com.wenhuang.sprites;

import com.wenhuang.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sprites {

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

    public String overlap(int x, int y) {
        ArrayList<Sprites> sprites = Main.gameView.getElements();
        for (Sprites s : sprites) {
            if (s.getRelativePosition() != null && x == s.getRelativePosition().x && y == s.getRelativePosition().y) {
                return "CANNOT MOVE";
            }
        }
        return "null";
    }
}
