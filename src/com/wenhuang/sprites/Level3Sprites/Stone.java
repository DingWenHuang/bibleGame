package com.wenhuang.sprites.Level3Sprites;

import com.wenhuang.sprites.Sprites;

import javax.swing.*;

public class Stone extends Sprites {

    public Stone(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("stone.png");
    }

    @Override
    public String overlap(int x, int y) {
        return super.overlap(x, y);
    }
}