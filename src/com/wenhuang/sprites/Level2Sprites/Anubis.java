package com.wenhuang.sprites.Level2Sprites;

import com.wenhuang.sprites.Sprites;

import javax.swing.*;

public class Anubis extends Sprites {

    public Anubis(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("anubis.png");
    }

    @Override
    public String overlap(int x, int y) {
        return super.overlap(x, y);
    }
}