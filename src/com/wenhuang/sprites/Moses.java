package com.wenhuang.sprites;

import com.wenhuang.Main;
import com.wenhuang.gameview.Level1GameView;
import com.wenhuang.sprites.Level1Sprites.Bug;
import com.wenhuang.sprites.Level1Sprites.Frog;
import com.wenhuang.sprites.Level1Sprites.Ice;
import com.wenhuang.sprites.Level1Sprites.Tombstone;

import javax.swing.*;
import java.util.ArrayList;

public class Moses extends Sprites {

    public Moses(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("Moses.png");
    }

    @Override
    public String overlap(int x, int y) {
        if (Main.gameView instanceof Level1GameView) {
            ArrayList<Bug> bugs = ((Level1GameView) Main.gameView).getBugs();
            ArrayList<Frog> frogs = ((Level1GameView) Main.gameView).getFrogs();
            for (Bug sprites : bugs) {
                if (sprites.getRelativePosition() != null && sprites.getRelativePosition().x == x && sprites.getRelativePosition().y == y) {
                    return "DIE";
                }
            }
            for (Frog sprites : frogs) {
                if (sprites.getRelativePosition() != null && sprites.getRelativePosition().x == x && sprites.getRelativePosition().y == y) {
                    return "DIE";
                }
            }
            ArrayList<Ice> ices = ((Level1GameView) Main.gameView).getIces();
            ArrayList<Tombstone> tombstones = ((Level1GameView) Main.gameView).getTombstones();
            for (Ice sprites : ices) {
                if (sprites.getRelativePosition() != null && sprites.getRelativePosition().x == x && sprites.getRelativePosition().y == y) {
                    return "CANNOT MOVE";
                }
            }
            for (Tombstone sprites : tombstones) {
                if (sprites.getRelativePosition() != null && sprites.getRelativePosition().x == x && sprites.getRelativePosition().y == y) {
                    return "CANNOT MOVE";
                }
            }
        }
        return "null";
    }
}
