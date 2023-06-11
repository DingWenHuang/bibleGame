package com.wenhuang.sprites.Level1Sprites;

import com.wenhuang.sprites.Sprites;

import javax.swing.*;

public class Frog extends Sprites {

    public Frog(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("frog.png");
    }

    @Override
    public String overlap(int x, int y) {
        return "null";
    }
}