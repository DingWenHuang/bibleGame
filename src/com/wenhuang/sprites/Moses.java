package com.wenhuang.sprites;

import com.wenhuang.Main;
import com.wenhuang.gameview.Level1GameView;
import com.wenhuang.gameview.Level2GameView;
import com.wenhuang.gameview.Level3GameView;
import com.wenhuang.sprites.Level1Sprites.Bug;
import com.wenhuang.sprites.Level1Sprites.Frog;
import com.wenhuang.sprites.Level1Sprites.Ice;
import com.wenhuang.sprites.Level1Sprites.Tombstone;
import com.wenhuang.sprites.Level2Sprites.Anubis;
import com.wenhuang.sprites.Level2Sprites.Cat;
import com.wenhuang.sprites.Level2Sprites.Pharaoh;
import com.wenhuang.sprites.Level3Sprites.Stone;

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
            Door door = Main.gameView.getDoor();
            if (x == door.getRelativePosition().x && y == door.getRelativePosition().y) {
                return "NEXT";
            }
        }
        if (Main.gameView instanceof Level2GameView) {
            ArrayList<Anubis> anubis = ((Level2GameView) Main.gameView).getAnubis();
            ArrayList<Pharaoh> pharaohs = ((Level2GameView) Main.gameView).getPharaohs();
            for (Anubis sprites : anubis) {
                if (sprites.getRelativePosition() != null && sprites.getRelativePosition().x == x && sprites.getRelativePosition().y == y) {
                    return "DIE";
                }
            }
            for (Pharaoh sprites : pharaohs) {
                if (sprites.getRelativePosition() != null && sprites.getRelativePosition().x == x && sprites.getRelativePosition().y == y) {
                    return "DIE";
                }
            }
            ArrayList<Cat> cats = ((Level2GameView) Main.gameView).getCats();
            for (Cat sprites : cats) {
                if (sprites.getRelativePosition() != null && sprites.getRelativePosition().x == x && sprites.getRelativePosition().y == y) {
                    return "CANNOT MOVE";
                }
            }
            Door door = Main.gameView.getDoor();
            if (x == door.getRelativePosition().x && y == door.getRelativePosition().y) {
                return "NEXT";
            }
        }
        if (Main.gameView instanceof Level3GameView) {
            ArrayList<Stone> stones = ((Level3GameView) Main.gameView).getStones();
            for (Stone sprites : stones) {
                if (sprites.getRelativePosition() != null && sprites.getRelativePosition().x == x && sprites.getRelativePosition().y == y) {
                    sprites.setNullPosition();
                    ((Level3GameView) Main.gameView).setCount(1);
                }
                if (((Level3GameView) Main.gameView).getCount() == 10) {
                    return "WIN";
                }
            }
        }
        return "null";
    }
}
