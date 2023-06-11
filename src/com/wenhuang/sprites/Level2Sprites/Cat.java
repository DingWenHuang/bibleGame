package com.wenhuang.sprites.Level2Sprites;

import com.wenhuang.sprites.Sprites;

import javax.swing.*;

public class Cat extends Sprites {

    public Cat(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("cat.png");
    }

    @Override
    public String overlap(int x, int y) {
        return super.overlap(x, y);
    }
}