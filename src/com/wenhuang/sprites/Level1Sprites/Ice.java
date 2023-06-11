package com.wenhuang.sprites.Level1Sprites;

import com.wenhuang.sprites.Sprites;

import javax.swing.*;

public class Ice extends Sprites {

    public Ice(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("ice.png");
    }

    @Override
    public String overlap(int x, int y) {
        return "null";
    }
}