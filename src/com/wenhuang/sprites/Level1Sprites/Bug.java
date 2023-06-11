package com.wenhuang.sprites.Level1Sprites;

import com.wenhuang.sprites.Sprites;

import javax.swing.*;

public class Bug extends Sprites {

    public Bug(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("bug.png");
    }

    @Override
    public String overlap(int x, int y) {
        return super.overlap(x, y);
    }
}