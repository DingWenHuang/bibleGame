package com.wenhuang.gameview;

import com.wenhuang.sprites.Level3Sprites.Stone;

import javax.swing.*;
import java.util.ArrayList;

public class Level3GameView extends GameView{

    public ArrayList<Stone> getStones() {
        return stones;
    }

    ArrayList<Stone> stones = new ArrayList<>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count += count;
    }

    int count;

    public Level3GameView () {
        img = new ImageIcon("mountain.jpg");
        elements = new ArrayList<>();
        count = 0;

        stones.add(new Stone(5, 5));
        stones.add(new Stone(2, 2));
        stones.add(new Stone(4, 10));
        stones.add(new Stone(7, 9));
        stones.add(new Stone(5, 2));
        stones.add(new Stone(3, 6));
        stones.add(new Stone(2, 6));
        stones.add(new Stone(3, 3));
        stones.add(new Stone(5, 8));
        stones.add(new Stone(4, 5));

        elements.addAll(stones);
    }
}

