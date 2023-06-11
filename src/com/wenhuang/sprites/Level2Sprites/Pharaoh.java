package com.wenhuang.sprites.Level2Sprites;

import com.wenhuang.sprites.Sprites;

import javax.swing.*;

public class Pharaoh extends Sprites {

    public Pharaoh(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("pharaoh.png");
    }

    @Override
    public String overlap(int x, int y) {
        return super.overlap(x, y);
    }
}