package com.wenhuang.sprites.Level1Sprites;

import com.wenhuang.sprites.Sprites;

import javax.swing.*;

public class Tombstone extends Sprites {

    public Tombstone(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("tombstone.png");
    }

    @Override
    public String overlap(int x, int y) {
        return super.overlap(x, y);
    }
}