package com.wenhuang.gameview;

import com.wenhuang.Main;
import com.wenhuang.sprites.Door;
import com.wenhuang.sprites.Sprites;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class GameView {

    public Door getDoor() {
        return door;
    }

    protected ArrayList<Sprites> elements;
    protected ImageIcon img;
    protected Door door;

    public void drawView(Graphics g) {
        img.paintIcon(null, g, 0, 0);
        g.setColor(new Color(0, 0, 0, 0.5f));
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        for (Sprites sprites : elements) {
            sprites.drawSprite(g);
        }
    }
}
